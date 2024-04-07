package com.leeonscoding.wsdtest.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(indexes = @Index(columnList = "name"))
public class Product extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String desc;

    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private int quantity;
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "wishlist_product",
            joinColumns = {
                    @JoinColumn(name = "product_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "wish_list_id", referencedColumnName = "id")
            }
    )
    private WishList wishList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "order_product",
            joinColumns = {
                    @JoinColumn(name = "product_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "order_id", referencedColumnName = "id")
            }
    )
    private Order order;
}

