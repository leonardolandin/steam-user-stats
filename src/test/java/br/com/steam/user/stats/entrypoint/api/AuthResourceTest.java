package br.com.steam.user.stats.entrypoint.api;

import br.com.steam.user.stats.application.dto.UserDTO;
import br.com.steam.user.stats.config.MongoDbConfig;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.quarkus.test.common.WithTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@WithTestResource(MongoDbConfig.class)
public class AuthResourceTest {

    private static final String EMAIL = "teste@teste.com";

    @Test
    void givenEmptyNameWhenCreateUserThenReturnBadRequestStatus() {
        given()
                .when()
                .contentType(ContentType.JSON)
                .body(UserDTO.builder().build())
                .post("/auth/create-user")
                .then()
                .statusCode(HttpResponseStatus.BAD_REQUEST.code())
                .body("message", is("É necessário informar um nome"));
    }

    @Test
    void givenEmptyEmailWhenCreateUserThenReturnBadRequestStatus() {
        given()
                .when()
                .contentType(ContentType.JSON)
                .body(UserDTO.builder()
                        .name(UUID.randomUUID().toString())
                        .build())
                .post("/auth/create-user")
                .then()
                .statusCode(HttpResponseStatus.BAD_REQUEST.code())
                .body("message", is("É necessário informar um e-mail"));
    }

    @Test
    void givenEmptyUsernameWhenCreateUserThenReturnBadRequestStatus() {
        given()
                .when()
                .contentType(ContentType.JSON)
                .body(UserDTO.builder()
                        .name(UUID.randomUUID().toString())
                        .email(EMAIL)
                        .build())
                .post("/auth/create-user")
                .then()
                .statusCode(HttpResponseStatus.BAD_REQUEST.code())
                .body("message", is("É necessário informar um nome de usuário"));
    }

    @Test
    void givenExistsUserWhenCreateUserThenReturnBadRequestStatus() {
        given()
                .when()
                .contentType(ContentType.JSON)
                .body(UserDTO.builder()
                        .name(UUID.randomUUID().toString())
                        .email(EMAIL)
                        .username(UUID.randomUUID().toString())
                        .build())
                .post("/auth/create-user")
                .then()
                .statusCode(HttpResponseStatus.OK.code());

        given()
                .when()
                .contentType(ContentType.JSON)
                .body(UserDTO.builder()
                        .name(UUID.randomUUID().toString())
                        .email(EMAIL)
                        .username(UUID.randomUUID().toString())
                        .build())
                .post("/auth/create-user")
                .then()
                .statusCode(HttpResponseStatus.BAD_REQUEST.code())
                .body("message", is("Ocorreu um erro inesperado"));
    }
}
