package edu.comillas.icai.gitt.pat.spring.jpa.Controlador;

import static org.junit.jupiter.api.Assertions.*;

import edu.comillas.icai.gitt.pat.spring.jpa.Model.ProfileResponse;
import edu.comillas.icai.gitt.pat.spring.jpa.Model.RegisterRequest;
import edu.comillas.icai.gitt.pat.spring.jpa.Servicio.UserServiceInterface;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UserController.class)
class UserControllerIntegrationTest {

    private static final String NAME = "Name";
    private static final String EMAIL = "name@email.com";

    @Autowired private MockMvc mockMvc;

    @MockBean private UserServiceInterface userService;

    @Test void registerOk() throws Exception {
        // Given ...
        Mockito.when(userService.profile(Mockito.any(RegisterRequest.class)))
                .thenReturn(new ProfileResponse(NAME, EMAIL));
        String request = "{" +
                "\"name\":\"" + NAME + "\"," +
                "\"email\":\"" + EMAIL + "\"," +
                "\"password\":\"aaaaaaA1\"}";
        // When ...
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                // Then ...
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("{" +
                        "\"name\":\"" + NAME + "\"," +
                        "\"email\":\"" + EMAIL + "\"," ));}

        @Test void registerInvalidPassword() throws Exception {
            // Given ...
            Mockito.when(userService.profile(Mockito.any(RegisterRequest.class)))
                    .thenReturn(new ProfileResponse(NAME, EMAIL));
            String request2 = "{" +
                    "\"name\":\"" + NAME + "\"," +
                    "\"email\":\"" + EMAIL + "\"," +
                    "\"password\":\"aa\"}";

            // When ...
            this.mockMvc
                    .perform(MockMvcRequestBuilders.post("/api/users/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(request2))

                    // Then ...
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());

        }

    }