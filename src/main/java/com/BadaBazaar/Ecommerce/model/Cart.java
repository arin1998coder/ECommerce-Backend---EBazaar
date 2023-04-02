package com.BadaBazaar.Ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int cartTotal;

    @OneToOne
    @JoinColumn
    private Customer customer;

    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
    private List<Item> itemList = new ArrayList<>();
}
