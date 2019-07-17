package learn.micronaut;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Single;

@Controller("/goodreads")
public class GoodreadsController {

    @Get("/{name}")
    public Single<String> helloGoodreader(String name) {
        return Single.just("Hello, " + name + "! You are a goodreader!");
    }
}