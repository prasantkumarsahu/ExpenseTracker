package com.prasant.expenseTracker.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Integer price;
    private LocalDate date;
    private LocalTime time;
    @ManyToOne
    private User user;

    public Product(String title, String description, Integer price, User user) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.user = user;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }
}
