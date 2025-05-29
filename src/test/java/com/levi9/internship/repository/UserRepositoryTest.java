package com.levi9.internship.repository;

import com.levi9.internship.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();

        user = User.builder()
                .userId("123")
                .username("john_doe")
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .build();

        userRepository.save(user);
    }

    @Test
    void testGetUserByUsername() {
        Optional<User> result = userRepository.getUserByUsername("john_doe");

        assertThat(result).isPresent();
        assertThat(result.get().getEmail()).isEqualTo("john@example.com");
    }

    @Test
    void testGetUsersByFirstName() {
        List<User> users = userRepository.getUsersByFirstName("John");

        assertThat(users).hasSize(1);
        assertThat(users.get(0).getUsername()).isEqualTo("john_doe");
    }

    @Test
    void testDeleteByUsername() {
        userRepository.deleteByUsername("john_doe");

        Optional<User> result = userRepository.getUserByUsername("john_doe");

        assertThat(result).isNotPresent();
    }

    @Test
    void testGetUserByUserId() {
        Optional<User> result = userRepository.getUserByUserId("123");

        assertThat(result).isPresent();
        assertThat(result.get().getUsername()).isEqualTo("john_doe");
    }
}
