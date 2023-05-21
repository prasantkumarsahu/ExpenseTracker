package com.prasant.expenseTracker.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInput {
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    @Min(1)
    private Integer price;
}
