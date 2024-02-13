package com.example.demo;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import com.example.demo.services.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private UserDao userDao;
    private UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp() {
        userDao = Mockito.mock(UserDao.class);
        userServiceImpl = new UserServiceImpl(userDao);
    }

    @Test
    void testCreateUser() {
        // given
        String userName = "TestUser";
        String password = "TestPass";

        // when
        userServiceImpl.createUser(userName, password);

        // then
        verify(userDao, times(1)).save(any(User.class));
    }
}
