package ru.bog;

/**
 * Created by zac on 29.03.17.
 */

public class IdResponse {
    private Long userId;

    public IdResponse(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
