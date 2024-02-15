package com.example.demo.dao;

import com.example.demo.Storage;
import com.example.demo.model.User;
import com.example.demo.model.UserKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * UserDao should be used to manage storage operations
 */
@Slf4j
public class UserDao {
    private final Map<UserKey, User> userMap;
    private final Storage storage;

    public UserDao(Storage database) {
        this.userMap = database.getUserMap();
        this.storage = database;
    }

    public void save(User user) {
        UserKey userKey = new UserKey(user.getUserId());
        userMap.put(userKey, user);
        storage.writeToFile();
        log.info("User is saved to BD: {}", user);
    }

    public User getById(UserKey id) {
        return userMap.get(id);
    }

    public User update(User user) {
        UserKey userKey = new UserKey(user.getUserId());
        User existingUser = userMap.get(userKey);
        if (existingUser != null) {
            existingUser.setUserName(user.getUserName());
            existingUser.setPassportData(user.getPassportData());
            log.info("User is updated: {}", user);
            return existingUser;
        } else {
            return null;
        }
    }

    public User delete(UserKey id) {
        return userMap.remove(id);
    }
}
