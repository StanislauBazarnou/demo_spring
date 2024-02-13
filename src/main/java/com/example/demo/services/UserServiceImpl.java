package com.example.demo.services;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * UserServiceImpl should hold business logic
 * <p>
 * In Spring, dependency injection can be done via setter methods, and this is an example of that.
 * In this case, the UserDao userDao is a dependency of the UserServiceImpl class
 * <p>
 * When you annotate the setter method setUserDao with @Autowired, you're telling Spring to
 * automatically find a bean of type UserDao in the application context and pass it to the setUserDao
 * method to inject it into the UserServiceImpl class as a dependency
 * <p>
 * This way the UserServiceImpl doesn't need to manually create or look for a UserDao instance;
 * Spring automatically provides it. This is called Dependency Injection and it's a core feature
 * of Spring. It helps in writing cleaner, more modular code
 */

@Slf4j
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public void createUser(String userName, String password) {
        int nextId = getNextUserId(loadingJsonDataFromFile());

        User user = new User();
        user.setUserId(nextId);
        user.setUserName(userName);
        user.setPassportData(password);

        userDao.save(user);
        log.info("User is created: {}", user);
    }

    public User getUser(long userId) {
        return userDao.getById(userId);
    }

    public User updateUser(User updatedUser) {
        log.info("User is updated: {}", updatedUser);
        return userDao.update(updatedUser);
    }

    public User deleteUser(long userId) {
        log.info("User is deleted: {}", userId);
        return userDao.delete(userId);
    }

    private List<ObjectNode> loadingJsonDataFromFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream jsonData = UserServiceImpl.class.getResourceAsStream("/Users.json");
        try {
            return objectMapper.readValue(jsonData, new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int getNextUserId(List<ObjectNode> users) {
        int maxId = 0;
        for (ObjectNode user : users) {
            int id = user.get("userId").asInt();
            if (id > maxId) {
                maxId = id;
            }
        }
        return maxId + 1;
    }
}
