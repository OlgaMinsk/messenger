package com.innowisegroup.messenger.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innowisegroup.messenger.dto.request.UserCreateRequest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(value = {"/sql/create-schema-before.sql", "/sql/create-table-user-before.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserController userController;

    @Autowired
    private ObjectMapper objectMapper;

    private final String url ="/messengerAPI/v01/users";

    @Test
    @Sql(value = {"/sql/create-user-before.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/create-user-after.sql"},
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void getAllUsers() throws Exception {
        String expectedContent = "[{\"id\":1,\"userName\":\"David\"},{\"id\":2,\"userName\":\"Mary\"},{\"id\":3,\"userName\":\"Mike\"},{\"id\":4,\"userName\":\"Nadya\"}]";
        int size = 4;
        this.mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(size)))
                .andExpect(content().string(expectedContent));
    }

    @Test
    @Sql(value = {"/sql/create-user-before.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/create-user-after.sql"},
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void getUserByIdPositive() throws Exception {
      //  String urlGetUserByIdPositive = "/messengerAPI/v01/users/1";
        String urlGetUserByIdPositive = url+"/1";
        String expectedContent = "{\"id\":1,\"userName\":\"David\"}";
        this.mockMvc.perform(MockMvcRequestBuilders.get(urlGetUserByIdPositive))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(expectedContent));
    }

    @Test
    @Sql(value = {"/sql/create-user-before.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/create-user-after.sql"},
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void getUserByIdNegative() throws Exception {
     //   String urlGetUserByIdNegative = "/messengerAPI/v01/users/10";
           String urlGetUserByIdNegative = url+"/10";
        this.mockMvc.perform(MockMvcRequestBuilders.get(urlGetUserByIdNegative))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @Sql(value = {"/sql/create-user-before.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/create-user-after.sql"},
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void createNewUserPositive() throws Exception {
        UserCreateRequest user = new UserCreateRequest();
        user.setUserName("V");
        String expectedContent = "{\"id\":10,\"userName\":\"V\"}";
        this.mockMvc.perform(post(url)
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(expectedContent));
    }

    @Test
    @Sql(value = {"/sql/create-user-before.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/create-user-after.sql"},
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void createNewUserNegativeDuplicateUniqueValue() throws Exception {
        UserCreateRequest user = new UserCreateRequest();
        user.setUserName("Mike");
        this.mockMvc.perform(post(url)
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Sql(value = {"/sql/create-user-before.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/create-user-after.sql"},
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void saveNegativeEmpty() throws Exception {
        UserCreateRequest user = new UserCreateRequest();
        user.setUserName("");
        this.mockMvc.perform(post(url)
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
