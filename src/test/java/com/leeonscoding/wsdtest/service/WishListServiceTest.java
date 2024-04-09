package com.leeonscoding.wsdtest.service;

import com.leeonscoding.wsdtest.repository.UserRepository;
import com.leeonscoding.wsdtest.repository.WishListRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class WishListServiceTest {

    @Mock
    private WishListRepository wishListRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private WishListService wishListService;


}
