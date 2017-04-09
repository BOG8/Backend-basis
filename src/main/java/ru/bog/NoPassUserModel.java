package ru.bog;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by zac on 10.04.17.
 */

public class NoPassUserModel {
    private Long id;
    private String login;
    private String email;

    public NoPassUserModel(ResultSet resultSet) throws SQLException {
        id = resultSet.getLong("id");
        login = resultSet.getString("login");
        email = resultSet.getString("email");
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }
}
