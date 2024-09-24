package com.globantTesting.testController;

import com.globant.Auth;
import com.globant.dto.UserDto;
import domain.user.DocumentIdentity;
import domain.user.DocumentType;
import com.globant.service.UserService;
import com.globantTesting.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// at 100% :)
@SpringBootTest(classes = Auth.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;



    @Test
    public void createUserApi() throws Exception {
     UserDto userDto = new UserDto(new DocumentIdentity(1234L, DocumentType.CC), "Glober", "Globant", "glober.globant@globant.com", 399555666L, "Connecta G6-G7");

        Mockito.when(userService.createUser(any(UserDto.class))).thenReturn(userDto);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users")
                        .content(JsonUtil.asJsonString(userDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("Glober"))
                .andExpect(jsonPath("$.documentIdentity.documentNumber").value(1234L));
    }

    @Test
    public void getAllUsersApi() throws Exception {
        UserDto userDto = new UserDto(new DocumentIdentity(1234L, DocumentType.CC), "Glober", "Globant", "glober.globant@globant.com", 399555666L, "Connecta G6-G7");

        Mockito.when(userService.getAllUsersSorted()).thenReturn(List.of(userDto));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Glober"));
    }


    @Test
    public void getUserByDocumentNumberApi() throws Exception {
        UserDto userDto = new UserDto(new DocumentIdentity(1234L, DocumentType.CC), "Glober", "Globant", "glober.globant@globant.com", 399555666L, "Connecta G6-G7");

        Mockito.when(userService.getUserByDocumentNumber(eq(1234L))).thenReturn(userDto);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/document/{documentNumber}", 1234L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Glober"));
    }

    @Test
    public void updateUserApi() throws Exception {
        UserDto userDto = new UserDto(new DocumentIdentity(1234L, DocumentType.CC), "UpdatedName", "UpdatedLastName", "updated.email@globant.com", 399555666L, "Updated Address");

        Mockito.when(userService.updateUser(eq(1234L), any(UserDto.class))).thenReturn(userDto);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/users/{documentNumber}", 1234L)
                        .content(JsonUtil.asJsonString(userDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteUserApi() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/users/{documentNumber}", 1234L))
                .andExpect(status().isNoContent());
    }

    @Test
    public void getUserByFirstNameApi() throws Exception {
        UserDto userDto = new UserDto(new DocumentIdentity(1234L, DocumentType.CC), "Glober", "Globant", "glober.globant@globant.com", 399555666L, "Connecta G6-G7");

        Mockito.when(userService.findUserByNameContaining(eq("Glober"))).thenReturn(List.of(userDto));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/name/{firstName}", "Glober")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Glober"));
    }


}
