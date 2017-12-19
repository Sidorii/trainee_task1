package com.epam.trainee.service.impl;

import com.epam.trainee.model.dao.DaoFactory;
import com.epam.trainee.model.dao.UserDao;
import com.epam.trainee.model.entities.User;
import com.epam.trainee.model.exceptions.AuthenticationException;
import com.epam.trainee.service.UserService;

import java.util.Objects;

public class UserServiceImpl implements UserService {

    private static final UserServiceImpl INSTANCE = new UserServiceImpl();

    public static UserServiceImpl getInstance() {
        return INSTANCE;
    }

    private UserDao userDao;

    public UserServiceImpl() {
        this.userDao = DaoFactory.getInstance().getUserDao();
    }


    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User registerUser(User user) {
        Objects.requireNonNull(user, "Register user can't be null");
        throwIfUserExists(user);
        checkUserRoles(user);
        return userDao.createUser(user);
    }

    private void throwIfUserExists(User user) {
        if (userDao.contains(user)) {
            throw new AuthenticationException(user, "User already exists");
        }
    }

    private void checkUserRoles(User user) {
        if (user.getRoles() == null || user.getRoles().size() == 0) {
            throw new AuthenticationException(user, "User must have at least one role");
        }
    }

    @Override
    public User findUserByEmail(String email) {
        Objects.requireNonNull(email, "Email can't be null");
        return userDao.getUserByEmail(email);
    }
}