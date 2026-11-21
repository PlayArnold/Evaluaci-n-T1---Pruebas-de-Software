Feature: Registro de cursos

  Scenario: Registro de curso válido
    Given que tengo los datos de un curso válido
    When envío la solicitud para registrar el curso
    Then el sistema responde con código 200
    And devuelve el nombre "Selenium Avanzado"

  Scenario: Registro con precio inválido
    Given que tengo los datos de un curso con precio inválido
    When envío la solicitud para registrar el curso
    Then el sistema responde con un código de error