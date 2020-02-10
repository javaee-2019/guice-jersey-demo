import com.demo.model.SingleModel;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

public class ClientTest {

    private static Client client = Client.create();

    @Test
    public void post() {
        String host = "http://localhost:8081";
        String url = "common/test/post/model";

        WebResource resource = client.resource(host);
        Form queryParam = new Form();
        queryParam.add("name", "jack");

        Map<String, String> bodyEntity = new HashMap<>();
        bodyEntity.put("name", "jack");

        String s = resource.path(url)
                .queryParams(queryParam)//url参数
                .type(MediaType.APPLICATION_JSON_TYPE)//请求application 类别
                .accept(MediaType.APPLICATION_JSON_TYPE)//接受json类型
                .post(String.class, bodyEntity);//--发送post请求 String接受 --发送的body参数

        System.out.println("s = " + s);

    }

    @Test
    public void get() {
        String host = "http://localhost:8081";
        String url = "common/test/get";

        Form queryParam = new Form();
        queryParam.add("name", "jack");

        WebResource resource = client.resource(host);
        String s = resource.path(url)
                .queryParams(queryParam)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(String.class);


        System.out.println("s = " + s);

    }
}
