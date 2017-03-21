package ru.bog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

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

}
