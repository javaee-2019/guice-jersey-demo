package com.demo.service.impl;

import com.demo.service.GuiceDemo;
import com.demo.util.JsonUtil;
import com.demo.util.SignatureGenerator;
import com.google.inject.Singleton;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;

import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class GuiceDemoImpl implements GuiceDemo {

    private static Client client = Client.create();

    @Override
    public void say() {
        System.out.println("new Date().toLocaleString() = " + new Date().toLocaleString());
    }

    @Override
    public String upload(File file) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String host = "http://jmcuat.timanetwork.com/";
        String url = "fileserver/fileServerInternal/addFile";
        String domain = "http://jmcuat.timanetwork.com/resource";
        String appkey = "5732477868";
        String secretKey = "94a7cbbf8511a288d22d4cf8705d61d0";
        WebResource resource = client.resource(host);
        //构造URL参数
        Form queryParam = new Form();
        queryParam.add("appkey", appkey);

        //开始签名
        Map<String, String> params = new HashMap<>();
        params.put("appkey", appkey);
        String sign = SignatureGenerator.generate(url, params, secretKey);
        //签名结果
        System.out.println("sign = " + sign);

        //构造URL签名参数
        queryParam.add("sign", sign);
        //构造body参数
//        File file = new File("C:\\Users\\TimaNetworks\\Desktop\\会议.txt");
        final FormDataMultiPart multiPart = new FormDataMultiPart();
        multiPart.bodyPart(new FileDataBodyPart("file", file,
                MediaType.APPLICATION_OCTET_STREAM_TYPE));
        multiPart.field("path", "/hf-mng/canId");
        multiPart.field("description", "测试文件上传");

        String s = resource.path(url)
                .queryParams(queryParam)//url参数
                .type(MediaType.MULTIPART_FORM_DATA)//请求application 类别
                .post(String.class, multiPart);//--发送post请求 String接受 --发送的body参数

        System.out.println("结果 = " + s);
        Map<String, Object> map = JsonUtil.json2Map(s);
        String uploadPath = domain + ((Map)map.get("detail")).get("fileUrl");
        System.out.println("uploadPath = " + uploadPath);
        return s;
    }
}
