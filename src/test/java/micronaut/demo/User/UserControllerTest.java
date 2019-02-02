package micronaut.demo.User;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import micronaut.demo.modules.user.model.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;

public class UserControllerTest {

    private static EmbeddedServer server;
    private static HttpClient client;

    @BeforeClass
    public static void setupServer() {
        server = ApplicationContext.run(EmbeddedServer.class);
        client = server
                .getApplicationContext()
                .createBean(HttpClient.class, server.getURL());
    }

    @AfterClass
    public static void stopServer() {
        if (server != null) {
            server.stop();
        }
        if (client != null) {
            client.stop();
        }
    }

    @Test
    public void All_Everyone_True() throws Exception {
        HttpRequest request = HttpRequest.GET("/users");
        HttpResponse response = client.toBlocking().exchange(request, User.class);
        assertEquals(HttpStatus.OK, response.getStatus());
    }

    @Test
    public void Register_User_True() throws Exception {
        User user = getUser();
        HttpRequest request = HttpRequest.POST("/users", user);
        HttpResponse response = client.toBlocking().exchange(request, User.class);
        assertEquals(HttpStatus.CREATED, response.getStatus());
    }

    public User getUser() {
        User user = new User();
        user.setName("Test");
        user.setBirthdate(new Date(1986, 11, 20));
        user.setEmail("teste@teste.com");
        user.setPassword("micronaut");
        return user;
    }
}
