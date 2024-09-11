package com.globant.domain.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(name = "user_id", nullable = false)
    private Long documentNumber;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<CartComboEntity> combos;

    @Column(name = "extra_information")
    private String extraInformation;

    @Column(name = "subtotal")
    private Float subtotal;
}
