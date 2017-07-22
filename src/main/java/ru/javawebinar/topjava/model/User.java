package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by promoscow on 22.07.17.
 */
public class User {
    private final LocalDateTime dateTime;
    private final String name;
    private final int age;

    public User(LocalDateTime dateTime, String name, int age) {
        this.dateTime = dateTime;
        this.name = name;
        this.age = age;
    }

    public LocalDate getDateTime() {
        return dateTime.toLocalDate();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "dateTime=" + dateTime +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
