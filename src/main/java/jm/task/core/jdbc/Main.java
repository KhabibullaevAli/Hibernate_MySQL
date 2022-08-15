package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.createUsersTable();
        userDao.saveUser("Vasya", "Ivanov", (byte) 5);
        userDao.saveUser("Masha", "Katina", (byte) 10);
        userDao.saveUser("Nic", "Barinov", (byte) 15);
        userDao.saveUser("Alina", "Katova", (byte) 20);
        userDao.removeUserById(4L);
        List<User> userList = userDao.getAllUsers();
        for (User u : userList) {
            System.out.println(u);
        }
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
