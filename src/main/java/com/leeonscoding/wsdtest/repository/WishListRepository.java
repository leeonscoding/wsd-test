package com.leeonscoding.wsdtest.repository;

import com.leeonscoding.wsdtest.entities.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<WishList, Long> {
}
