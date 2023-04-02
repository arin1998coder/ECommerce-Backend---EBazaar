package com.BadaBazaar.Ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int requiredQuantity;

    @ManyToOne
    @JoinColumn
    private Cart cart;

    @OneToOne
    @JoinColumn
    private Product product;

    @ManyToOne
    @JoinColumn
    private Ordered ordered;

}
