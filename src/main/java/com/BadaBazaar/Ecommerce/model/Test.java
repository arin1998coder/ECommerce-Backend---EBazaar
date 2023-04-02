package com.BadaBazaar.Ecommerce.model;

import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tid;

    private int id;

    private String name;

    @Singular("list")
    private List<String> list = new ArrayList<>();

}


