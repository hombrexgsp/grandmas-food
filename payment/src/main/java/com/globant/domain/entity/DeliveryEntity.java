package com.globant.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "delivery")
public class DeliveryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JoinColumn(table = "orders", name = "uuid", referencedColumnName = "uuid")
    private UUID orderId;

    @Column(name = "delivery_time")
    private LocalDateTime deliveryTime;
}


