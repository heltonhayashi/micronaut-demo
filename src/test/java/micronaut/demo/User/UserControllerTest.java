package micronaut.demo.User;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.runtime.server.EmbeddedServer;
import micronaut.demo.modules.user.model.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

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
    public void Get_IdEqualsOne_Empty() throws Exception {
        HttpRequest request = HttpRequest.GET("/users/1");
        String body = client.toBlocking().retrieve(request);
        assertEquals(
                body,
                "{}"
        );
    }

    @Test
    public void Register_User_True() throws Exception {
        User user = getUser();
        HttpRequest request = HttpRequest.POST("/users", user);
        HttpResponse response = client.toBlocking().exchange(request, User.class);
        assertEquals(HttpStatus.CREATED, response.getStatus());
    }

    @Test(expected = HttpClientResponseException.class)
    public void Delete_User_ExpectExcetion() throws Exception {
        HttpRequest request = HttpRequest.DELETE("/users/1");
        client.toBlocking().retrieve(request);
    }

    @Test(expected = HttpClientResponseException.class)
    public void Update_User_ExpectExcetion() throws Exception {
        HttpRequest request = HttpRequest.DELETE("/users/1");
        client.toBlocking().retrieve(request);
    }

    public User getUser() {
        User user = new User();
        user.setName("Test");
        user.setBirthdate("20/11/1986");
        user.setEmail("teste@teste.com");
        user.setPassword("micronaut");
        return user;
    }

}
