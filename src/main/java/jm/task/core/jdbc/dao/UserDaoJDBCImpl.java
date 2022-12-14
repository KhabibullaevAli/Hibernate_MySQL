package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;
    private Statement statement;
    //    private PreparedStatement prepStatAddingUser;
    private PreparedStatement prepStatRemovingUser;

    public UserDaoJDBCImpl() {
        connection = Util.getConnection();
        try {
            this.statement = connection.createStatement();
//            prepStatAddingUser = connection.prepareStatement("insert into users (name, lastName, age) values (?,?,?)");
            prepStatRemovingUser = connection.prepareStatement("delete from users where id = ?");
        } catch (SQLException throwables) {
            System.out.println("Cant get statement");
        }
    }

    public void createUsersTable() {
        try {
            statement.execute("CREATE TABLE IF NOT EXISTS `pp_bd_113`.`users` " +
                    "(`id` INT NOT NULL AUTO_INCREMENT," +
                    "`name` VARCHAR(45) NOT NULL," +
                    "`lastName` VARCHAR(45) NOT NULL," +
                    "`age` INT(3) NOT NULL, " +
                    "PRIMARY KEY (`id`), UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)");
        } catch (SQLException throwables) {
            System.out.println("Cant create UsersTable");
        }
    }

    public void dropUsersTable() {
        try {
            statement.executeUpdate("DROP TABLE `users`");
        } catch (SQLException throwables) {
            System.out.println("Cant dropUsersTable");
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            statement.executeUpdate("insert into users (name, lastName, age) values ('" + name + "','" + lastName + "'," + age + ")");
//            prepStatAddingUser.setString(1, name);
//            prepStatAddingUser.setString(2, lastName);
//            prepStatAddingUser.setInt(3, age);
//            prepStatAddingUser.execute();
            System.out.println("User ?? ???????????? ??? " + name + " ???????????????? ?? ???????? ????????????");
        } catch (SQLException throwables) {
            System.out.println("Cant saveUser");
        }
    }

    public void removeUserById(long id) {
        try {
            prepStatRemovingUser.setLong(1, id);
            prepStatRemovingUser.execute();
        } catch (SQLException throwables) {
            System.out.println("Cant removeUserById");
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new LinkedList<>();
        try {
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                User user = new User();
                user.setId((long) resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge((byte) resultSet.getInt(4));
                list.add(user);
            }
        } catch (SQLException throwables) {
            System.out.println("cant getAllUsers");
        }
        return list;
    }

    public void cleanUsersTable() {
        try {
            statement.executeUpdate("delete from users");
//            statement.executeUpdate("truncate table users");
        } catch (SQLException throwables) {
            System.out.println("Cant cleanUsersTable");
        }
    }
}
