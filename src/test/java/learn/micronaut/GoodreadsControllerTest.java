package learn.micronaut;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;

import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class GoodreadsControllerTest {

    private EmbeddedServer server;
    private HttpClient client;

    @BeforeEach
    public void setup() {
        this.server = ApplicationContext.run(EmbeddedServer.class);
        this.client = HttpClient.create(this.server.getURL());
    }

    @Test
    public void shouldReturnHello() {
        String response = client.toBlocking()
                .retrieve(HttpRequest.GET("/goodreads/Stefano"));
        assertEquals(response, "Hello, Stefano! You are a goodreader!");
    }

    @AfterEach
    public void cleanup() throws IOException {
        this.server.stop();
        this.client.close();
    }
}
