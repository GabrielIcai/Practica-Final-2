package edu.comillas.icai.gitt.pat.spring.jpa.Servicio;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import edu.comillas.icai.gitt.pat.spring.jpa.Entidades.AppUser;
import edu.comillas.icai.gitt.pat.spring.jpa.Entidades.Planta;
import edu.comillas.icai.gitt.pat.spring.jpa.Repositorio.AppUserRepository;
import edu.comillas.icai.gitt.pat.spring.jpa.Repositorio.RepoPlanta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ServicioPlantasTest {

    @Mock
    private RepoPlanta repoPlanta;

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private ServicioPlantas servicioPlantas;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreaPlanta() {
        // Arrange
        String nombre = "Mi Planta";
        Duration duracion = Duration.ofMinutes(30);
        AppUser appUser = new AppUser();
        Planta planta = new Planta();
        planta.nombre=nombre;
        planta.appUser=appUser;
        planta.temporizadorRiego=(LocalDateTime.now());
        planta.Regada=(false);
        planta.tiempoRiego=(duracion);

        when(repoPlanta.save(any(Planta.class))).thenReturn(planta);

        // Act
        Planta result = servicioPlantas.creaPlanta(nombre, duracion, appUser);

        // Assert
        verify(repoPlanta).save(any(Planta.class));
        assertEquals(nombre, result.nombre);
        assertEquals(appUser, result.appUser);
        assertEquals(duracion, result.tiempoRiego);

    }

    @Test
    public void testBorraPlanta() {
        // Arrange
        Planta planta = new Planta();

        // Act
        servicioPlantas.borra(planta);

        // Assert
        verify(repoPlanta).delete(planta);
    }
    @Test
    public void testBuscaPlantas() {
        // Arrange
        AppUser appUser = new AppUser();
        Planta planta1 = new Planta();
        planta1.appUser=appUser;
        Planta planta2 = new Planta();
        planta2.appUser=appUser;

        List<Planta> plantas = Arrays.asList(planta1, planta2);

        when(repoPlanta.findByAppUser(appUser)).thenReturn(plantas);

        // Act
        Iterable<Planta> result = servicioPlantas.buscaplantas(appUser);

        // Assert
        assertIterableEquals(plantas, result);
    }
}
