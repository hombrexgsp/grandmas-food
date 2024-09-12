package com.globant.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "cart_products")
public class CartComboEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "uuid", nullable = false)
    private OrderEntity order;

    @Column(name = "combo_id")
    private UUID comboId;

    @Column(name = "quantity")
    private Integer quantity;

}
