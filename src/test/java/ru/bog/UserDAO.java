package ru.bog;

import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by zac on 21.03.17.
 */

@Repository
public class UserDAO {
    protected DataSource dataSource;

    @Autowired
    public UserDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Nullable
    public UserModel registration(UserModel user) {
        final Connection connection = DataSourceUtils.getConnection(dataSource);
        final String query = "INSERT INTO users(login, password, email) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.executeUpdate();
            try (ResultSet resultSet = ps.getGeneratedKeys()) {
                resultSet.next();
                user.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            return null;
        }

        return user;
    }

    @Nullable
    public UserModel getIdByLogin(String login) {
        final Connection connection = DataSourceUtils.getConnection(dataSource);
        final String query = "SELECT id, password FROM users WHERE login = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, login);
            try (ResultSet resultSet = ps.executeQuery()) {
                resultSet.next();
                return new UserModel(resultSet.getLong("id"), resultSet.getString("password"));
            }
        } catch (SQLException e) {
            return null;
        }
    }

    @Nullable
    public NoPassUserModel getUserInfo(long userId) {
        final Connection connection = DataSourceUtils.getConnection(dataSource);
        final String query = "SELECT id, login, email FROM Users WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, userId);
            try (ResultSet resultSet = ps.executeQuery()) {
                resultSet.next();
                return new NoPassUserModel(resultSet);
            }
        } catch (SQLException e) {
            return null;
        }
    }
}
