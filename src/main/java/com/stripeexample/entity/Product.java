package com.stripeexample.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "product_info")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;


    private String description;


    private double price;
}
