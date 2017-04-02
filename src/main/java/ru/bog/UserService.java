package ru.bog;

import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zac on 21.03.17.
 */

@Service
@Transactional
public class UserService {
    private UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Nullable
    public UserModel registration(UserModel user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDAO.registration(user);
    }

    @Nullable
    public Long getUserId(UserModel user) {
        final UserModel userReply = userDAO.getIdByLogin(user.getLogin());
        if (userReply != null) {
            if (passwordEncoder.matches(user.getPassword(), userReply.getPassword())) {
                return userReply.getId();
            }
        }

        return null;
    }

}