package com.leeonscoding.wsdtest.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Entity
public class WishList extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(mappedBy = "wishList")
    private User user;

    @OneToMany(mappedBy = "wishList")
    private List<Product> products;
}

