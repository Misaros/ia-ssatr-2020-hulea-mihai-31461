/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.rentacar.service;

import ro.utcluj.rentacar.connection.DbAccess;
import ro.utcluj.rentacar.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Martin
 */
public class UserService {
    DbAccess dbAccess;

    public UserService() throws ClassNotFoundException, SQLException {
        this.dbAccess = DbAccess.getInstance();
    }

    public String addANewUser(User user) throws SQLException {
        Random random = new Random();
        int id = random.nextInt(999999999);
        try (Statement s = dbAccess.getConnection().createStatement()) {
            s.executeUpdate("INSERT INTO USERS (id, FIRSTNAME, LASTNAME, AGE, CNP, DRIVEINGEXPERIENCE) VALUES ("+id+",'" + user.getFirstName() + "','" + user.getLastName() + "'," + user.getAge() + ",'" + user.getCnp() + "'," + user.getDriveingExperience() + ")");
        }
        return "Successfully inserted new car: " + user.toString();
    }

    public List<User> readUsers() throws SQLException {
        try (Statement s = dbAccess.getConnection().createStatement()) {
            ResultSet resultSet = s.executeQuery("SELECT * FROM USERS");
            if (resultSet.next()) {
                User user = createUserFromResultSet(resultSet);

                List<User> users = new ArrayList<>();
                users.add(user);
                while (resultSet.next()) {
                    user = new User();
                    setFields(resultSet, user);
                    users.add(user);
                }
                return users;
            } else {
                return new ArrayList<>(0);
            }
        }
    }

    public String deleteUserById(Integer userId) throws SQLException {
        try (Statement s = dbAccess.getConnection().createStatement()) {
            User user = findUserById(userId);
            if (user != null) {
                s.executeUpdate("DELETE FROM USERS WHERE ID = " + userId);
                return "Car with id = " + userId + " deleted successfully";
            } else {
                return "Delete failed, user with id = " + userId + " not found";
            }
        }
    }

    public User findUserById(Integer userId) throws SQLException {
        try (Statement s = dbAccess.getConnection().createStatement()) {
            ResultSet resultSet = s.executeQuery("SELECT * FROM USERS WHERE ID = " + userId);
            if (resultSet.next()) {
                return createUserFromResultSet(resultSet);
            } else {
                return null;
            }
        }
    }

    private void setFields(ResultSet resultSet, User user) throws SQLException {
        user.setId(resultSet.getLong("id"));
        user.setFirstName(resultSet.getString("firstname"));
        user.setLastName(resultSet.getString("lastname"));
        user.setAge(resultSet.getDouble("age"));
        user.setDriveingExperience(resultSet.getDouble("driveingexperience"));
        user.setCnp(resultSet.getString("cnp"));
    }

    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        setFields(resultSet, user);
        return user;
    }
    
}
