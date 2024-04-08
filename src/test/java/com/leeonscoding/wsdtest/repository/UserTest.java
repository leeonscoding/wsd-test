package com.leeonscoding.wsdtest.repository;

import com.leeonscoding.wsdtest.entities.Role;
import com.leeonscoding.wsdtest.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {

        User user1 = User.builder()
                .email("hanif@mail.com")
                .password("Hello@1234")
                .status(true)
                .role(Role.CUSTOMER)
                .build();
        user1.setCreatedAt(LocalDateTime.now());
        user1.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user1);

        User user2 = User.builder()
                .email("rabby@mail.com")
                .password("Hello@1234")
                .status(true)
                .role(Role.CUSTOMER)
                .build();
        user2.setCreatedAt(LocalDateTime.now());
        user2.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user2);

        User user3 = User.builder()
                .email("admin@mail.com")
                .password("Hello@1234")
                .status(true)
                .role(Role.ADMIN)
                .build();
        user3.setCreatedAt(LocalDateTime.now());
        user3.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user3);
    }


    @Test
    public void createUserWithValidEmailAndValidPassword() {

        String email = "john@mail.com";
        String password = "Hello@1234";

        User user = User.builder()
                .email(email)
                .password(password)
                .status(true)
                .role(Role.CUSTOMER)
                .build();
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        User createdUser = userRepository.save(user);
        assertEquals(createdUser.getEmail(), email);
        assertEquals(createdUser.getPassword(), password);
    }


    @Test
    public void createUserWithInvalidEmailAndValidPassword() {
        String email = "john";
        String password = "Hello@1234";

        User createdUser = null;

        try {
            User user = User.builder()
                    .email(email)
                    .password(password)
                    .status(true)
                    .role(Role.CUSTOMER)
                    .build();

            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());

            createdUser = userRepository.save(user);
        } catch (Exception e) {

        }

        assertNull(createdUser);
    }

    @Test
    public void findByEmail() {
        Optional<User> userOptional = userRepository.findByEmail("hanif@mail.com");

        assertTrue(userOptional.isPresent());
    }


}
