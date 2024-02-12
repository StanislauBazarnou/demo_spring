package com.example.demo.dao;

import com.example.demo.Storage;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * UserDao should be used to manage storage operations
 */
public class UserDao {
    private final Map<Long, User> userMap;
    private final Storage storage;
    private final UserDao userDao;

    @Autowired
    public UserDao(Storage database, Storage storage, UserDao userDao) {
        this.userMap = database.getUserMap();
        this.storage = storage;
        this.userDao = userDao;
    }

    public void save(User user) {
        userMap.put(user.getUserId(), user);
        storage.writeToFile();
    }

    public User getById(long id) {
        return userMap.get(id);
    }

    public User update(User user) {
        User existingUser = userDao.getById(user.getUserId());
        if (existingUser != null) {
            existingUser.setUserName(user.getUserName());
            existingUser.setPassportData(user.getPassportData());
            return existingUser;
        } else {
            return null;
        }
    }

    public User delete(long id) {
        return userMap.remove(id);
    }
}
