package com.globant.model.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "uuid")
    private final UUID uuid;




    @JoinColumn(name = "users.document_number")
    @Column(name = "userid")
    private final long documentNumber;


    @Column(name = "creation_time")
    private final LocalDateTime creationTime;

    @Column(name = "extra_information")
    private final String extraInformation;


    private final Float subtotal;
}
