package com.lkamdem.User;

import com.lkamdem.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

public class UserRepositoryImpl implements UserRepository{
    private final DatabaseConnection db = DatabaseConnection.getDatabaseConnection();
    private final Connection dbcon = db.getConnection();
    @Override
    public boolean save(User user) {
        if(user != null){
            try(PreparedStatement stmt = dbcon.prepareStatement("INSERT INTO users (user_id, name, email, password, telephone, role) VALUES(?, ?, ?, ?,?, ?)");
            ){

            stmt.setString(1, user.getUserId());
            stmt.setString(2, user.getUserName());
            stmt.setString(3, user.getUserEmail());
            stmt.setString(4, user.getUserPassword());
            stmt.setString(5, user.getTelephoneNumber());
            stmt.setString(6, user.getUserRole().toString());
            stmt.executeUpdate();
            return true;
            } catch(SQLException e){
                System.out.println(e.getMessage()); // TODO: Replace with logging when GUI implemented
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean delete(String userId) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User findById(String userId) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public Set<User> findAll() {
        return Set.of();
    }
}
