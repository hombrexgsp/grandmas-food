package com.globantTestingProducts.ProductoControllerTest;

import com.globant.Products;
import com.globant.service.ComboService;
import domain.combo.Category;
import domain.combo.Combo;
import domain.combo.CreateCombo;
import domain.combo.error.ComboException.ComboNotFound;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Products.class)
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ComboService comboService;

    @Test
    public void getAllCombos() throws Exception {
        Mockito.when(comboService.getAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/products")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    public void getComboByUuid() throws Exception {
        UUID uuid = UUID.randomUUID();
        Combo combo = new Combo(uuid, "Combo 1", null, "Description", 10.0f, true);

        Mockito.when(comboService.searchByUuid(uuid)).thenReturn(combo);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/products/{uuid}", uuid)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").value(uuid.toString()))
                .andExpect(jsonPath("$.fantasyName").value("Combo 1"));
    }

    @Test
    public void addCombo() throws Exception {
        Combo combo = new Combo(UUID.randomUUID(), "Combo 1", Category.HAMBURGERS_AND_HOTDOGS, "Description", 10.0f, true);
        CreateCombo createCombo = new CreateCombo("Combo 1", Category.HAMBURGERS_AND_HOTDOGS, "Description", 10.0f, true);

        Mockito.when(comboService.addCombo(Mockito.any(CreateCombo.class))).thenReturn(combo);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"fantasyName\": \"Combo 1\", \"category\": \"HAMBURGERS_AND_HOTDOGS\", \"description\": \"Description\", \"price\": 10.0, \"available\": true }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.fantasyName").value("Combo 1"))
                .andExpect(jsonPath("$.description").value("Description"));
    }


    @Test
    public void deleteCombo() throws Exception {
        UUID uuid = UUID.randomUUID();

        Mockito.doNothing().when(comboService).deleteCombo(uuid);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/products/{uuid}", uuid))
                .andExpect(status().isNoContent());
    }

    @Test
    public void searchComboByName() throws Exception {
        Combo combo = new Combo(UUID.randomUUID(), "Combo 1", null, "Description", 10.0f, true);

        Mockito.when(comboService.searchByName(Mockito.anyString())).thenReturn(Collections.singletonList(combo));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/products/search")
                        .param("q", "Combo 1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fantasyName").value("Combo 1"));
    }

    @Test
    public void getComboByUuid_NotFound() throws Exception {
        UUID uuid = UUID.randomUUID();

        Mockito.when(comboService.searchByUuid(uuid)).thenThrow(new ComboNotFound(uuid));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/products/{uuid}", uuid)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


}
