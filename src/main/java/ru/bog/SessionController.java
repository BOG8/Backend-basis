package ru.bog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by zac on 29.03.17.
 */

@RestController
@RequestMapping(value = "/api/session")
public class SessionController {
    public static final String USER_ID = "userId";
    private final UserController userController;

    @Autowired
    public SessionController(UserController userController) {
        this.userController = userController;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody UserModel body, HttpSession httpSession) {
        final Long userId = userController.getUserId(body);
        if (userId != null) {
            httpSession.setAttribute(USER_ID, userId);
            final IdResponse idResponse = new IdResponse(userId);
            return ResponseEntity.ok(idResponse);
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{}");
    }
}
