import com.demo.model.SingleModel;
import com.demo.util.SignatureGenerator;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


public class CommonTest {
    private final Logger logger = LoggerFactory.getLogger(CommonTest.class);

    @Test
    public void post() {
        Client client = Client.create();
        WebResource resource = client.resource("http://localhost:8080");
        HashMap<String, String> map = new HashMap<>();
        map.put("msg", "jack");
        SingleModel s = resource.path("/demo/hello/post")
                .entity(map, MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(SingleModel.class);
        System.out.println("s = " + s);

    }

    @Test
    public void get() {
        Client client = Client.create();
        WebResource resource = client.resource("http://localhost:8080");
        String s = resource.path("/demo/hello/get")
                .queryParam("msg", "jack")
                .get(String.class);
        System.out.println("s = " + s);

    }

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

}
