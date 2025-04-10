package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {

    }

    Connection connection = getConnection();

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT auto_increment primary key, " +
                "name VARCHAR(255), " +
                "last_Name VARCHAR(255), " +
                "age TINYINT)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            System.out.println("Table created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            System.out.println("Table dropped");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "insert into users (name, last_name, age) values (?, ?, ?)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = "delete from users where id = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "select * from users";
        List<User> usersList = new ArrayList<>();
        try (ResultSet resultSet = getConnection().createStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "delete from users";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            System.out.println("Table cleaned");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
