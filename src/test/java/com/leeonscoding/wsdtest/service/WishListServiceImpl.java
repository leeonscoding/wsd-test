package com.leeonscoding.wsdtest.service;

import com.leeonscoding.wsdtest.entities.Product;
import com.leeonscoding.wsdtest.entities.User;
import com.leeonscoding.wsdtest.exceptions.APIException;
import com.leeonscoding.wsdtest.repository.UserRepository;
import com.leeonscoding.wsdtest.repository.WishListRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WishListServiceImpl implements WishListService{

    private WishListRepository wishListRepository;

    private UserRepository userRepository;

    public WishListServiceImpl(WishListRepository wishListRepository, UserRepository userRepository) {
        this.wishListRepository = wishListRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Product> getWishListByUser(long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            return userOptional.get().getWishList().getProducts();
        }
        throw new APIException("Something went wrong.");
    }

    @Override
    public void addProduct(List<Product> productList, long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        User user = null;
        List<Product> products = new ArrayList<>();
        if (userOptional.isPresent()) {
            user = userOptional.get();
            products.addAll(user.getWishList().getProducts());
        } else {
            throw new APIException("Something went wrong.");
        }

        products.addAll(productList);
        user.getWishList().setProducts(products);

        userRepository.save(user);

    }
}
