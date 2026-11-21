package com.pruebas.api;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;

class CursoApiTest {

    @Test
    void consultarTodosLosCursos() {

        given()
                .when()
                .get("http://localhost:8080/api/cursos")
                .then()
                .statusCode(200)
                .body("$", hasSize(greaterThanOrEqualTo(3)));
    }
}