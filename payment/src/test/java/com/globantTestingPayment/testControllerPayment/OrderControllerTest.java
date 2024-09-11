package com.globantTestingPayment.testControllerPayment;

import com.globant.Payment;
import com.globant.domain.delivery.Delivered;
import com.globant.domain.delivery.Pending;
import com.globant.domain.order.CreateOrder;
import com.globant.domain.order.CreatedOrder;
import com.globant.domain.order.Order;
import com.globant.service.OrderService;
import domain.cart.CartTotal;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Payment.class)
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    public void getOrderById() throws Exception {
        UUID uuid = UUID.randomUUID();
        Order order = new Order(uuid, LocalDateTime.now(), 123L, List.of(), "Test order", 100f);

        Mockito.when(orderService.getOrder(uuid)).thenReturn(order);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/orders/{uuid}", uuid)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value(uuid.toString()))
                .andExpect(jsonPath("$.documentNumber").value(123L));
    }

    @Test
    public void createOrder() throws Exception {
        UUID uuid = UUID.randomUUID();
        CartTotal cartTotal = new CartTotal(List.of(), 100f); // Incluimos el total
        CreateOrder createOrder = new CreateOrder(123L, cartTotal, "Test order");

        CreatedOrder createdOrder = new CreatedOrder(uuid, LocalDateTime.now(), 123L, List.of(), "Test order", 100f, 19f, 119f, new Pending());

        // Mocking the service
        Mockito.when(orderService.createOrder(Mockito.any(CreateOrder.class))).thenReturn(createdOrder);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"documentNumber\": 123, \"cart\": {\"products\": [], \"total\": 100}, \"extraInformation\": \"Test order\"}")) // Incluimos el campo total
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.uuid").value(uuid.toString()))
                .andExpect(jsonPath("$.userId").value(123L)); // Cambiado a `userId` si ese es el campo correcto
    }


    @Test
    public void markAsDelivered() throws Exception {
        UUID uuid = UUID.randomUUID();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        CreatedOrder createdOrder = new CreatedOrder(uuid, LocalDateTime.now(), 123L, List.of(), "Test order", 100f, 19f, 119f, new Delivered(LocalDateTime.now()));

        Mockito.when(orderService.setDelivery(Mockito.eq(uuid), Mockito.any(LocalDateTime.class))).thenReturn(createdOrder);

        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/orders/{uuid}/delivered/{timestamp}", uuid, timestamp)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").value(uuid.toString()))
                .andExpect(jsonPath("$.delivery.deliveredDate").exists());
    }




}
