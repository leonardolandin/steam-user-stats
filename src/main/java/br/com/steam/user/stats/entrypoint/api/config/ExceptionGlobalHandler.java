package br.com.steam.user.stats.entrypoint.api.config;

import io.netty.handler.codec.http.HttpResponseStatus;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Provider
public class ExceptionGlobalHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        Response response = Response
                .status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code())
                .entity(new ResponseErrorDTO(e.getMessage()))
                .build();

        if (e instanceof WebApplicationException exception) {
            response = Response
                    .status(exception.getResponse().getStatus())
                    .entity(new ResponseErrorDTO(e.getMessage()))
                    .build();
        }

        return response;
    }

    @Data
    @AllArgsConstructor
    private static class ResponseErrorDTO {
        private String message;
    }
}