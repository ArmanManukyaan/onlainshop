package com.example.onlainShop.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@TableGenerator(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private double price;
    private String description;

    private String imgPath;
    @ManyToOne
    private Category category;

}
