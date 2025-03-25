package com.alibou.ecommece.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductResponse(

        Integer id,

        @NotNull(message = "Product name is required")
        String name,

        @NotNull(message = "Product description is required")
        String description,

        @Positive(message = "Product availableQuantity should be positive")
        double availableQuantity,

        @Positive(message = "Product price should be positive")
        BigDecimal price,

        @NotNull(message = "Product category is required")
        Integer categoryId,

        String categoryName,

        String categoryDescription

) {
}
