import com.demo.model.GetSnapshotV2Vo;
import com.demo.util.JsonUtil;
import com.demo.util.SignatureGenerator;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


public class CommonTest {
    private final Logger logger = LoggerFactory.getLogger(CommonTest.class);
    private static Client client = Client.create();
    String appkey = "5732477868";
    String secretKey = "94a7cbbf8511a288d22d4cf8705d61d0";
    @Test
    public void test38() {
        ClientConfig clientConfig = new DefaultClientConfig();
        Map<String, Object> properties = clientConfig.getProperties();
        Object property1 = clientConfig.getProperty(ClientConfig.PROPERTY_CONNECT_TIMEOUT);
        Object property2 = clientConfig.getProperty(ClientConfig.PROPERTY_READ_TIMEOUT);
        Client client = Client.create(clientConfig);

        String s = client.resource("http://www.baidu.com").get(String.class);
        System.out.println("s = " + s);
    }

    @Test
    public void test53() {
        Client client = Client.create();
        Form queryForm = new Form();
        queryForm.add("appkey", "asd");
        queryForm.add("sign", "asds");
        queryForm.add("signt", "123");
        queryForm.add("vin", "123VIN11111111111");
        queryForm.add("result", "Y");
        queryForm.add("date", "12312312321");
        Object response = client.resource("http://localhost:8088/").path("internal/sync/result/beiJStdSix")
                .queryParams(queryForm)
                .accept(MediaType.APPLICATION_JSON)
                .get(Map.class);

        System.out.println("response = " + response);
    }


    @Test
    public void test78() {
        beiJStdSixResultSync("123VIN11111111111", "Y", 1578989106713L);
    }


    public Object beiJStdSixResultSync(String vin, String result, Long date) {
        Client client = Client.create();
        logger.info("|VEHICLE_DETECTION|BEIJSTDSIX_RESULTSYNC|DATA: {},{},{}", vin, result, date);
        String signt = String.valueOf(System.currentTimeMillis());

        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("appkey", "");
        paramsMap.put("vin", vin);
        paramsMap.put("result", result);
        paramsMap.put("date", date.toString());
        String sign = null;

        Form queryForm = new Form();
        queryForm.add("appkey", "");
        queryForm.add("sign", sign);
        queryForm.add("signt", signt);
        queryForm.add("vin", vin);
        queryForm.add("result", result);
        queryForm.add("date", date);

        Object response = client.resource("http://172.20.32.85:10067/").path("internal/sync/result/beiJStdSix")
                .queryParams(queryForm)
                .accept(MediaType.APPLICATION_JSON)
                .get(Object.class);
        logger.info("|VEHICLE_DETECTION|BEIJSTDSIX_RESULTSYNC|SUCCESS|DATE: {}", response);
        return response;
    }


    @Test
    public void test113() {
        Client client = Client.create();
        Form queryForm = new Form();
        queryForm.add("vin", "LMGFE1G88D1000980");
        Object response = client.resource("http://172.20.66.213:9303/").path("iov-console/internal/metric/vehicleIsOnline")
                .queryParams(queryForm)
                .accept(MediaType.APPLICATION_JSON)
                .get(Map.class);

        System.out.println("response = " + response);
    }

    @Test
    public void test126() throws Exception {
        Client client = Client.create();
        Long signt = System.currentTimeMillis();
        Map<String, String> params = new HashMap<String, String>();
        params.put("appkey", "132");
        params.put("signt", Long.toString(signt));
        params.put("vin", "LMGFE1G88D1000980");
        String vehicleIsOnlineUri = "iov-console/internal/metric/vehicleIsOnline";
        String sign = SignatureGenerator.generate(vehicleIsOnlineUri, params, "");

        Form queryForm = new Form();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            queryForm.add(entry.getKey(), entry.getValue());
        }
        queryForm.add("sign", sign);

        String iovConsoleServer = "http://172.20.66.213:9303/";
        Object responseEntity = client
                .resource(iovConsoleServer)
                .path(vehicleIsOnlineUri)
                .queryParams(queryForm)
                .get(Map.class);
        System.out.println("responseEntity = " + responseEntity);
    }

    @Test
    public void post() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String host = "http://jmcuat.timanetwork.com/";
        String url = "fileserver/fileServerInternal/addFile";
        String domain = "http://jmcuat.timanetwork.com/resource";
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
        File file = new File("C:\\Users\\TimaNetworks\\Desktop\\dms同步.png");
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
        String uploadPath = domain + ((Map) map.get("detail")).get("fileUrl");
        System.out.println("uploadPath = " + uploadPath);

    }

    @Test
    public void test173() {
        String s = client.resource("http://localhost:8081/common/test/header")
                .queryParam("name", "jack")
                .header("token", "sdsd")
                .get(String.class);
        System.out.println("s = " + s);
    }

    @Test
    public void getSnapshotV2Uri() {
        Long signt = System.currentTimeMillis();
        Map<String, String> params = new HashMap<String, String>();
        params.put("appkey", appkey);
        params.put("signt", Long.toString(signt));
        String getSnapshotV2Uri="vehicle-status/internal/condition/getSnapshotV2";
        String vehicleStatusServer="http://172.20.66.166:8090";
        String sign = generateSign(getSnapshotV2Uri, params);

        Form queryForm = new Form();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            queryForm.add(entry.getKey(), entry.getValue());
        }
        queryForm.add("sign", sign);
        GetSnapshotV2Vo vo = new GetSnapshotV2Vo();
        vo.setVin("XUNFEIDEMO0000006");
        Object responseEntity = client
                .resource(vehicleStatusServer)
                .path(getSnapshotV2Uri)
                .queryParams(queryForm)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Object.class,vo);
        System.out.println("responseEntity = " + responseEntity);
    }

    private String generateSign(String uri, Map<String, String> params) {
        String sign = null;
        try {
            sign = SignatureGenerator.generate(uri, params, secretKey);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sign;
    }
}
