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

    @Test
    void consultarCursoPorId() {

        given()
                .when()
                .get("http://localhost:8080/api/cursos/1")
                .then()
                .statusCode(200)
                .body("nombre", org.hamcrest.Matchers.equalTo("Seguridad de Aplicaciones"))
                .body("vacantes", org.hamcrest.Matchers.greaterThan(0));
    }
}