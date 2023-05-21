package com.prasant.expenseTracker.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductOutput {
    private Long id;
    private String title;
    private String description;
    private Integer price;
    private LocalDate date;
    private LocalTime time;
}
