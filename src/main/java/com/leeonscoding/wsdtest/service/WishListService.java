package com.leeonscoding.wsdtest.service;

import com.leeonscoding.wsdtest.entities.Product;
import com.leeonscoding.wsdtest.entities.User;

import java.util.List;

public interface WishListService {
    List<Product> getWishListByUser(long userId);

    void addProduct(List<Product> productList, long userId);
}
