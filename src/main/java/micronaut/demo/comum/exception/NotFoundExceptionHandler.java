package micronaut.demo.comum.exception;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;

import javax.inject.Singleton;

@Produces(MediaType.APPLICATION_JSON)
@Singleton
@Requires(classes = {NotFoundException.class, ExceptionHandler.class})
public class NotFoundExceptionHandler implements ExceptionHandler <NotFoundException, HttpResponse> {

    @Override
    public HttpResponse handle(HttpRequest request, NotFoundException exception) {
        return HttpResponse.badRequest(new Error(exception.getMessage()));
    }
}
