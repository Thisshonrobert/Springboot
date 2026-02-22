package com.codewithmosh.store.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "cart_item", schema = "store_api")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    @NotNull It will be in DTO
    @ManyToOne( optional = false)
    // @OnDelete(action = OnDeleteAction.CASCADE) DONE IN DB LVL
    @JoinColumn(name = "cart_id")
    private Cart cart;


    @ManyToOne( optional = false)
    @JoinColumn(name = "product_id")
    private Product product;


    @ColumnDefault("1")
    @Column(name = "quantity")
    private Integer quantity;

    public BigDecimal getTotalPrice(){
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }


}