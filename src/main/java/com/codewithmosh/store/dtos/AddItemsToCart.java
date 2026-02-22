package com.codewithmosh.store.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;


@Data
public class AddItemsToCart {
    @NotNull
    private Long productId;
}