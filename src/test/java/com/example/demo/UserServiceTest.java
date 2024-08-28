package com.example.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception ignored) {}
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void getUserById() {
    }

    @Test
    void getUserByName() {
    }

    @Test
    void addUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser_noUserFound() {
        String userId = "testId";
        // Arrange
        when(userRepository.findById(anyString())).thenReturn(Optional.empty());

        // Act
        User user = userService.deleteUser(userId);

        // Assert
        assertNull(user);
        verify(userRepository, never()).deleteById(nullable(String.class));
        verify(userRepository, times(1)).findById(eq(userId));
    }
}