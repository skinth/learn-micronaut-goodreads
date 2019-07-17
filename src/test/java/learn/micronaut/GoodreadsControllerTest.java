/*
This file is part of goodreads.

goodreads is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

goodreads is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with goodreads.  If not, see <http://www.gnu.org/licenses/>.
*/

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
