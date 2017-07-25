package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

/**
 * Created by promoscow on 22.07.17.
 */
public class UsersUtil {
    public static final List<User> USERS = Arrays.asList(
            new User(LocalDateTime.of(2017, Month.APRIL, 13, 19, 0), "Charlie", 34),
            new User(LocalDateTime.of(2016, Month.DECEMBER, 22, 1, 0), "Jinger", 19),
            new User(LocalDateTime.of(2017, Month.JULY, 20, 8, 0), "Johny", 57)
    );
}