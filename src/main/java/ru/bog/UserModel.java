package ru.bog;

/**
 * Created by zac on 19.03.17.
 */

public class UserModel {
    private Long id;
    private String login;
    private String password;
    private String email;

    public UserModel() {

    }

    public UserModel(Long id, String password) {
        this.id = id;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

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

    public void setPassword(String password) {
        this.password = password;
    }
}
