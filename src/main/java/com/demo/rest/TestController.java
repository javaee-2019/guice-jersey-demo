package com.demo.rest;

import com.demo.model.FileDemoCmd;
import com.demo.model.HelloPostCommonCommand;
import com.demo.service.GuiceDemo;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Niu Li
 * @date 2016/7/24
 */
@Path("/test")
public class TestController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)//指定返回类型为text_plain
    public String helloWorld() {

        return "hello world!";
    }

    @POST
    @Path("file")
    @Consumes({MediaType.MULTIPART_FORM_DATA})  //指定接受类型
    @Produces(MediaType.APPLICATION_JSON)  //返回类型
    public Object importVehicleOwnerGb(@QueryParam("appkey") String appkey,
                                       @QueryParam("sign") String sign,
                                       @QueryParam("signt") String signt,
                                       @FormDataParam("file") File file,
                                       @FormDataParam("file") FormDataContentDisposition fileMetaData,
                                       @FormDataParam("file1") File file1,
                                       @FormDataParam("file1") FormDataContentDisposition fileMetaData1
    ) {

        return " SUCCEED: " + fileMetaData.getName() + "===" + fileMetaData1.getName();
    }

    @POST
    @Path("/file/upload")
    @Consumes({MediaType.MULTIPART_FORM_DATA})  //指定接受类型
    @Produces(MediaType.APPLICATION_JSON)  //返回类型
    public Object uploadFile(
            @HeaderParam("userId") int userId,
            @FormDataParam("file") File file,
            @FormDataParam("file") FormDataContentDisposition fileMetaData) throws Exception {
        //调用相应的service处理
        return fileMetaData.getName() + "====" + file.getName();
    }
}