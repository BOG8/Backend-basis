package ru.bog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zac on 21.03.17.
 */

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity registration(@RequestBody UserModel body) {
        final UserModel userReply = userService.registration(body);
        if (userReply != null) {
            return ResponseEntity.ok(userReply);
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{}");
    }

    public Long getUserId(UserModel user) {
        return userService.getUserId(user);
    }
}
