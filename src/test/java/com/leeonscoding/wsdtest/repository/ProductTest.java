package com.leeonscoding.wsdtest.repository;

import com.leeonscoding.wsdtest.entities.Product;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

@DataJpaTest
public class ProductTest {

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        Product p1 = Product.builder()
                .name("r15")
                .status(true)
                .price(500000)
                .quantity(5)
                .build();
        p1.setCreatedAt(LocalDateTime.now());
        p1.setUpdatedAt(LocalDateTime.now());

        productRepository.save(p1);

        Product p2 = Product.builder()
                .name("mt15")
                .status(true)
                .price(400000)
                .quantity(15)
                .build();
        p2.setCreatedAt(LocalDateTime.now());
        p2.setUpdatedAt(LocalDateTime.now());

        productRepository.save(p2);
    }

    @Test
    public void findByName() {
        Optional<Product> productOptional = productRepository.findByName("mt15");

        assertNotNull(productOptional);

        assertEquals(productOptional.get().getPrice(), 400000);

    }

}
