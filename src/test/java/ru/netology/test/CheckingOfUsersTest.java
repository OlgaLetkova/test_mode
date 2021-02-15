package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.data.InvalidRegistration;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CheckingOfUsersTest {

    @BeforeEach
    public void setUp() {
        DataGenerator.setUpAll();
    }

    @Test
    void shouldCheckOfExistingUser() {
        given()
                .baseUri("http://localhost:9999/api/system/users")
                .when()
                .get("/login")
                .then()
                .body("login", equalTo("vasya"));
    }

    @Test
    void shouldGetStatusOfUser() {
        given()
                .baseUri("http://localhost:9999/api/system/users")
                .when()
                .get("/status")
                .then()
                .body("status", equalTo("active"));
    }

    @Test
    void shouldCheckOfSendingInvalidLogin() {
       InvalidRegistration rewrite = new InvalidRegistration(123, "password", "blocked");
        given()
                .baseUri("http://localhost:9999/api/system/users")
                .body(rewrite)
                .when()
                .post("/post")
                .then()
                .statusCode(400)
                .body("login", equalTo(123));
    }
}
