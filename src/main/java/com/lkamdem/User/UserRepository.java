package com.lkamdem.User;

import java.sql.SQLException;
import java.util.Set;

public interface UserRepository {
    boolean save(User user) throws SQLException;
    boolean delete(String userId);
    boolean updateProfile(User user);
    boolean updatePassword(String userId, String password);
    User findById(String userId);
    User findByEmail(String email);
    Set<User> findAll();
}
