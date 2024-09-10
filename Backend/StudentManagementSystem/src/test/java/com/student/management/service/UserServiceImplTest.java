package com.student.management.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.student.management.entity.User;
import com.student.management.repo.UserRepo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceImplTest {

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserByName() {
        User user = new User();
        user.setUsername("john");

        when(userRepo.findByusername("john")).thenReturn(user);

        User foundUser = userService.getUserByName("john");
        assertNotNull(foundUser);
        assertEquals("john", foundUser.getUsername());
    }

    @Test
    void testSaveUserInDB() {
        User user = new User();
        user.setUsername("john");

        when(userRepo.save(user)).thenReturn(user);

        User savedUser = userService.saveUserInDB(user);
        assertNotNull(savedUser);
        assertEquals("john", savedUser.getUsername());
    }
}
