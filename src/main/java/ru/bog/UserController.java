package ru.bog;

import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static ru.bog.SessionController.USER_ID;

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

    @Nullable
    public Long getUserId(UserModel user) {
        return userService.getUserId(user);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity getUserInfo(@PathVariable long userId, HttpSession httpSession) {
        final Object object = httpSession.getAttribute(USER_ID);
        if (!(object instanceof Long)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{}");
        }
        final NoPassUserModel user = userService.getUserInfo(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{}");
    }
}
