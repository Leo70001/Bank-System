package com.lkamdem.User;

import com.lkamdem.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
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
        try(PreparedStatement stmt = dbcon.prepareStatement("DELETE FROM users WHERE user_id = ?")){
            stmt.setString(1, userId);
            stmt.executeUpdate();
            return true;

        }catch(SQLException e){
            System.out.println(e.getMessage()); //TODO: GUI
            return false;
        }
    }

    @Override
    public boolean updateProfile(User user) {
        try(PreparedStatement stmt = dbcon.prepareStatement("UPDATE users SET name = ?, email = ?, telephone = ? WHERE user_id = ?")){
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getUserEmail());
            stmt.setString(3, user.getTelephoneNumber());
            stmt.setString(4, user.getUserId());
            stmt.executeUpdate();
            return true;
        }catch (SQLException e){
            System.out.println(e.getMessage()); // TODO: GUI
            return false;
        }
    }

    @Override
    public boolean updatePassword(String userId, String password){
        try(PreparedStatement stmt = dbcon.prepareStatement("UPDATE users SET password = ? WHERE user_id = ?")){
            stmt.setString(1,password);
            stmt.setString(2, userId);
            stmt.executeUpdate();
            return true;
        }catch (SQLException e){
            System.out.println(e.getMessage()); //TODO: when GUI
            return false;
        }

    }
    private User mapUser(String query, String id_or_email){
        try(PreparedStatement stmt = dbcon.prepareStatement(query)){
        stmt.setString(1, id_or_email);
        ResultSet result = stmt.executeQuery();
        if(result.next()){
            return mapUserFromRow(result);
        }
        return null;
        }catch (SQLException e){
            System.out.println(e.getMessage()); // TODO: GUI
            return null;
        }
    }
    @Override
    public User findById(String userId) {
        return mapUser("SELECT * FROM users WHERE user_id = ?", userId);

    }

    @Override
    public User findByEmail(String email) {
       return mapUser("SELECT * FROM users WHERE email = ?", email);

    }

    private User mapUserFromRow(ResultSet result) throws SQLException{
            User user =  new User(result.getString("name"),result.getString("email"),
                    Role.valueOf(result.getString("role")), result.getString("telephone"));
            user.setInitialPassword(result.getString("password"));
            return user;

    }
    @Override
    public Set<User> findAll() {
        try(PreparedStatement stmt = dbcon.prepareStatement("SELECT * FROM users")) {
            Set<User> users = new HashSet<>();
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                    users.add(mapUserFromRow(result));
            }
            return users;

        }catch (SQLException e){
            System.out.println(e.getMessage()); // TODO: GUI
            return null;
        }
    }
}

/**
 * DESIGN DECISIONS:
 * - Implements UserRepository interface for abstraction
 * - DatabaseConnection Singleton for single db connection
 * - PreparedStatement for SQL injection prevention
 * - try-with-resources for automatic resource cleanup
 * - mapUserFromRow() helper to avoid code duplication (DRY principle)
 * - mapUser() helper abstracts query execution for findById/findByEmail
 * - Role.valueOf() to convert String back to enum
 * - Returns null when user not found (caller's responsibility to handle)
 * - TODO comments for replacing System.out.println with proper logging
 */