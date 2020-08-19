package ua.kerberos.search.specification.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ua.kerberos.search.specification.SearchDemoApplication;
import ua.kerberos.search.specification.entity.enumerators.SystemRoles;
import ua.kerberos.search.specification.entity.enumerators.UserStatuses;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = {SearchDemoApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerTest {

    @Autowired
    protected MockMvc mvc;


    @Test
    public void testSearchByFirstName() throws Exception {

        mvc.perform(get("/api/v1/users")
                .param("firstName", "John")
                .accept(APPLICATION_JSON_VALUE)
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.content.*").isArray())
                .andExpect(jsonPath("$.content[0].firstName").value("John"));

    }


    @Test
    public void testSearchByRoleId() throws Exception {

        mvc.perform(get("/api/v1/users")
                .param("role", SystemRoles.EXECUTOR.name())
                .param("firstName", "John")
                .accept(APPLICATION_JSON_VALUE)
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.content.*").isArray())
                .andExpect(jsonPath("$.content.*").value(hasSize(5)));

    }


    @Test
    public void testSearchByRegionId() throws Exception {
        mvc.perform(get("/api/v1/users")
                .param("regionId", "1")
                .accept(APPLICATION_JSON_VALUE)
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.content.*").isArray())
                .andExpect(jsonPath("$.content.*").value(hasSize(7)));

    }


    @Test
    public void testSearchByCountryId() throws Exception {
        mvc.perform(get("/api/v1/users")
                .param("countryId", "1")
                .accept(APPLICATION_JSON_VALUE)
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.content.*").isArray())
                .andExpect(jsonPath("$.content.*").value(hasSize(3)));

    }


    @Test
    public void testSearchByRegionName() throws Exception {
        mvc.perform(get("/api/v1/users")
                .param("regionName", "Cherkasy")
                .accept(APPLICATION_JSON_VALUE)
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.content.*").isArray())
                .andExpect(jsonPath("$.content.*").value(hasSize(3)));

    }

    @Test
    public void testSearchByCountryName() throws Exception {
        mvc.perform(get("/api/v1/users")
                .param("countryName", "uk")
                .accept(APPLICATION_JSON_VALUE)
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.content.*").isArray())
                .andExpect(jsonPath("$.content.*").value(hasSize(7)));

    }

    @Test
    public void testSearchByMultiple() throws Exception {
        mvc.perform(get("/api/v1/users")
                .param("countryName", "UK")
                .param("countryId", "1")
                .param("role", SystemRoles.EXECUTOR.name())
                .param("firstName", "John")
                .param("regionId", "1")
                .accept(APPLICATION_JSON_VALUE)
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.content.*").isArray())
                .andExpect(jsonPath("$.content.*").value(hasSize(3)));

    }


    @Test
    public void testSearchByStatus() throws Exception {

        mvc.perform(get("/api/v1/users")
                .param("status", UserStatuses.ACTIVE.name())
                .accept(APPLICATION_JSON_VALUE)
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.content.*").isArray())
                .andExpect(jsonPath("$.content[0].status").value(UserStatuses.ACTIVE.name()));

    }

}