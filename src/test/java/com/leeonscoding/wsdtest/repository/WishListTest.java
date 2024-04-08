package com.leeonscoding.wsdtest.repository;

import com.leeonscoding.wsdtest.entities.Product;
import com.leeonscoding.wsdtest.entities.Role;
import com.leeonscoding.wsdtest.entities.User;
import com.leeonscoding.wsdtest.entities.WishList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class WishListTest {

    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void addWishList() {
        //1st user
        User user1 = User.builder()
                .email("hanif@mail.com")
                .password("Hello@1234")
                .status(true)
                .role(Role.CUSTOMER)
                .build();
        user1.setCreatedAt(LocalDateTime.now());
        user1.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user1);

        //2nd user
        User user2 = User.builder()
                .email("rabby@mail.com")
                .password("Hello@1234")
                .status(true)
                .role(Role.CUSTOMER)
                .build();
        user2.setCreatedAt(LocalDateTime.now());
        user2.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user2);

        //1st product
        Product p1 = Product.builder()
                .name("r15")
                .status(true)
                .price(500000)
                .quantity(5)
                .build();
        p1.setCreatedAt(LocalDateTime.now());
        p1.setUpdatedAt(LocalDateTime.now());

        productRepository.save(p1);

        //2nd product
        Product p2 = Product.builder()
                .name("mt15")
                .status(true)
                .price(400000)
                .quantity(15)
                .build();
        p2.setCreatedAt(LocalDateTime.now());
        p2.setUpdatedAt(LocalDateTime.now());

        productRepository.save(p2);

        //3rd product
        Product p3 = Product.builder()
                .name("pulsar")
                .status(true)
                .price(180000)
                .quantity(8)
                .build();
        p3.setCreatedAt(LocalDateTime.now());
        p3.setUpdatedAt(LocalDateTime.now());

        productRepository.save(p3);

        //4th product
        Product p4 = Product.builder()
                .name("gixxer")
                .status(true)
                .price(210000)
                .quantity(21)
                .build();
        p4.setCreatedAt(LocalDateTime.now());
        p4.setUpdatedAt(LocalDateTime.now());

        productRepository.save(p4);

        //1st user wish list
        WishList wishList1 = WishList.builder()
                .user(user1)
                .products(new ArrayList<>(List.of(p1, p3)))
                .build();
        wishList1.setCreatedAt(LocalDateTime.now());
        wishList1.setUpdatedAt(LocalDateTime.now());
        wishListRepository.save(wishList1);

        //2nd user wish list
        WishList wishList2 = WishList.builder()
                .user(user2)
                .products(new ArrayList<>(List.of(p1, p4, p2)))
                .build();
        wishList2.setCreatedAt(LocalDateTime.now());
        wishList2.setUpdatedAt(LocalDateTime.now());
        wishListRepository.save(wishList2);

        //set wish list to users
        user1.setWishList(wishList1);
        userRepository.save(user1);

        user2.setWishList(wishList2);
        userRepository.save(user2);
    }

    @Test
    public void check1stUserWishList() {
        Optional<User> userOptional = userRepository.findByEmail("hanif@mail.com");

        Product p1 = productRepository.findByName("r15").orElseThrow();
        Product p3 = productRepository.findByName("pulsar").orElseThrow();

        userOptional.ifPresent(user -> {
            Assertions.assertEquals(List.of(p1, p3), user.getWishList().getProducts());
        });
    }

    @Test
    public void check2ndUserWishList() {
        Optional<User> userOptional = userRepository.findByEmail("rabby@mail.com");

        Product p1 = productRepository.findByName("r15").orElseThrow();
        Product p4 = productRepository.findByName("gixxer").orElseThrow();
        Product p2 = productRepository.findByName("mt15").orElseThrow();

        userOptional.ifPresent(user -> {
            Assertions.assertEquals(List.of(p1, p4, p2), user.getWishList().getProducts());
        });
    }
}
