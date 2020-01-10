import com.demo.model.SingleModel;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;


public class CommonTest {

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
    }
}
