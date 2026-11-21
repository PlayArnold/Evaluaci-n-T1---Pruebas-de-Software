package com.pruebas.model.services;

import com.pruebas.model.Inscripcion;
import com.pruebas.repository.InscripcionRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InscripcionServiceTest {

    @Mock
    private InscripcionRepository repository;

    @InjectMocks
    private InscripcionService service;

    @Test
    void inscribirEstudianteCorrectamente() {

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setCodigoEstudiante("I202504447");
        inscripcion.setCursoId(3L);

        when(repository.existsByCodigoEstudianteAndCursoId(
                "I202504447", 3L)).thenReturn(false);

        when(repository.save(inscripcion)).thenReturn(inscripcion);

        Inscripcion resultado = service.inscribir(inscripcion);

        assertNotNull(resultado);

        assertEquals("I202504447", resultado.getCodigoEstudiante());
        assertEquals(3L, resultado.getCursoId());

        verify(repository, times(1)).save(inscripcion);
    }

    @Test
    void noPermitirInscripcionDuplicada() {

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setCodigoEstudiante("I202504447");
        inscripcion.setCursoId(3L);

        when(repository.existsByCodigoEstudianteAndCursoId(
                "I202504447", 3L)).thenReturn(true);

        RuntimeException excepcion = assertThrows(
                RuntimeException.class,
                () -> service.inscribir(inscripcion)
        );

        assertEquals("Estudiante ya inscrito", excepcion.getMessage());

        verify(repository, never()).save(any(Inscripcion.class));
    }

    @Test
    void cancelarInscripcionExistente() {

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setId(1L);
        inscripcion.setCodigoEstudiante("I202504447");
        inscripcion.setCursoId(3L);

        when(repository.findById(1L))
                .thenReturn(java.util.Optional.of(inscripcion));

        assertDoesNotThrow(() -> service.cancelar(1L));

        verify(repository, times(1)).delete(inscripcion);
    }
}