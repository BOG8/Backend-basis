package ru.bog;

/**
 * Created by zac on 19.03.17.
 */

public class UserModel {
    private Long id;
    private String login;
    private String password;
    private String email;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
