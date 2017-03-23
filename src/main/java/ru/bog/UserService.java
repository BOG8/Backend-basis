package ru.bog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by zac on 21.03.17.
 */

@Service
public class UserService {
    private UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserModel registration(UserModel user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDAO.registration(user);
    }

}
