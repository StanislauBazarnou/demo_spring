package com.example.demo.services;

import com.example.demo.dao.UserDao;
import com.example.demo.model.TicketKey;
import com.example.demo.model.User;
import com.example.demo.model.UserKey;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
        UserKey userKey = new UserKey(userId);
        return userDao.getById(userKey);
    }

    public User updateUser(User updatedUser) {
        log.info("User is updated: {}", updatedUser);
        return userDao.update(updatedUser);
    }

    public User deleteUser(long userId) {
        log.info("User is deleted: {}", userId);
        UserKey userKey = new UserKey(userId);
        return userDao.delete(userKey);
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
