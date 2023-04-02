package com.BadaBazaar.Ecommerce.model;

import com.BadaBazaar.Ecommerce.enums.Category;
import com.BadaBazaar.Ecommerce.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int price;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @ManyToOne
    @JoinColumn
    private Seller seller;

    @OneToOne(mappedBy = "product",cascade = CascadeType.ALL)
    private Item item;

}
