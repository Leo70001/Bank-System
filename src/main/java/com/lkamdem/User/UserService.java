package com.lkamdem.User;

public interface UserService {
    User register(String userId, String email, String password);
    User login(String userId, String password);
    boolean logout(String userId);
    User findUserById(String userId);
    User findUserByEmail(String email);
    String createUser(String name, String email, String telephone);
    boolean closeUser(String userId);
   boolean unlockUser(String userId);
}
