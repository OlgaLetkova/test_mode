package ru.netology.data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import static io.restassured.RestAssured.given;

@Data
@RequiredArgsConstructor

public class DataGenerator {
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();


   public static void generateUser(String login, String password, String status) {

        given()
                .spec(requestSpec)
                .body(new RegistrationDto(login,password,status))
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }
}
