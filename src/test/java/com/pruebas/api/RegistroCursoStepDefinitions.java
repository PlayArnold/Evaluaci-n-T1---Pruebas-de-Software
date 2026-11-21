package com.pruebas.api;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

import io.restassured.response.Response;

public class RegistroCursoStepDefinitions {

    private String nombre;
    private Double precio;
    private Integer vacantes;
    private Response respuesta;

    @Given("que tengo los datos de un curso válido")
    public void datosCursoValido() {
        nombre = "Selenium Avanzado";
        precio = 400.0;
        vacantes = 20;
    }

    @Given("que tengo los datos de un curso con precio inválido")
    public void datosCursoPrecioInvalido() {
        nombre = "Selenium Avanzado";
        precio = 0.0;
        vacantes = 20;
    }

    @When("envío la solicitud para registrar el curso")
    public void enviarSolicitudRegistro() {

        respuesta = given()
                .contentType("application/json")
                .body("{"
                        + "\"nombre\":\"" + nombre + "\","
                        + "\"precio\":" + precio + ","
                        + "\"vacantes\":" + vacantes
                        + "}")
                .when()
                .post("http://localhost:8080/api/cursos");
    }

    @Then("el sistema responde con código 200")
    public void verificarCodigo200() {
        respuesta.then().statusCode(200);
    }

    @And("devuelve el nombre {string}")
    public void verificarNombre(String nombreEsperado) {
        respuesta.then()
                .body("nombre", equalTo(nombreEsperado));
    }

    @Then("el sistema responde con un código de error")
    public void verificarCodigoError() {
        respuesta.then()
                .statusCode(greaterThan(399));
    }
}
