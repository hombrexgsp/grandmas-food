package com.globantTesting.controllerTest;

import com.globant.Gateway;
import com.globant.http.ProductController;
import com.globant.resolvers.ProductResolver;
import domain.combo.Category;
import domain.combo.Combo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(classes = Gateway.class)
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductResolver productResolver;

    @Test
    void testAllCombosQuery() throws Exception {
        var comboList = List.of(new Combo(UUID.randomUUID(), "Combo 1", Category.HAMBURGERS_AND_HOTDOGS, "description", 10.0f, true));
        when(productResolver.getAll()).thenReturn(comboList);

        String graphqlQuery = "{ \"query\": \"{ allCombos { uuid fantasyName category price available } }\" }";

        mockMvc.perform(post("/graphql")
                        .content(graphqlQuery)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.allCombos[0].fantasyName").value("Combo 1"))
                .andExpect(jsonPath("$.data.allCombos[0].price").value(10.0));
    }


    @Test
    void testCreateComboMutation() throws Exception {
        var newCombo = new Combo(UUID.randomUUID(), "Combo 1", Category.HAMBURGERS_AND_HOTDOGS, "description", 10.0f, true);
        when(productResolver.addCombo(Mockito.any())).thenReturn(newCombo);

        String graphqlMutation = "{ \"query\": \"mutation { createCombo(input: { fantasyName: \\\"Combo 1\\\", category: FOOD, description: \\\"description\\\", price: 10.0, available: true }) { uuid fantasyName category price available } }\" }";

        mockMvc.perform(post("/graphql")
                        .content(graphqlMutation)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.createCombo.fantasyName").value("Combo 1"))
                .andExpect(jsonPath("$.data.createCombo.price").value(10.0));
    }


}
