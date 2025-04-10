package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        userServiceImpl.createUsersTable();
        userServiceImpl.saveUser("Alex","Shev", (byte) 26);
        userServiceImpl.saveUser("Dima","Shep", (byte) 29);
        userServiceImpl.saveUser("Artem","Sark", (byte) 28);
        userServiceImpl.saveUser("Masha","Kuz", (byte) 25);
        userServiceImpl.getAllUsers().forEach(System.out::println);
        userServiceImpl.cleanUsersTable();
        userServiceImpl.dropUsersTable();
    }
}
