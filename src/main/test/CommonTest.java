import org.glassfish.jersey.client.ClientProperties;
import org.junit.Test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class CommonTest {

    @Test
    public void test() {

    }

    @Test
    public void test1() {
        javax.ws.rs.client.Client client = ClientBuilder.newClient();
        client.property(ClientProperties.CONNECT_TIMEOUT, 30000);//连接建立超时时间
        client.property(ClientProperties.READ_TIMEOUT, 30000);//读取内容超时时间
        String url = "http://www.baidu.com";
        Response response = client.target(url).request(MediaType.APPLICATION_FORM_URLENCODED)
                .get();
        System.out.println("response = " + response);

    }
}
