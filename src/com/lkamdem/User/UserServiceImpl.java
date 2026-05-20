package com.lkamdem.User;

public class UserServiceImpl implements UserService{
    @Override
    public User register(String userId, String email, String password) {
        return null;
    }

    @Override
    public User login(String userId, String password) {
        return null;
    }

    @Override
    public boolean logout(String userId) {
        return false;
    }

    @Override
    public User findUserById(String userId) {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    @Override
    public String createUser(String name, String email, String telephone) {
        return "";
    }

    @Override
    public boolean closeUser(String userId) {
        return false;
    }

    @Override
    public boolean unlockUser(String userId) {
        return false;
    }
}
