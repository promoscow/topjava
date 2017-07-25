package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by promoscow on 22.07.17.
 */
public class User {
    private Integer id;
    private final LocalDateTime dateTime;
    private final String name;
    private final int age;

    public User(LocalDateTime dateTime, String name, int age) {
        this(null, dateTime, name, age);
    }

    public User(Integer id, LocalDateTime dateTime, String name, int age) {
        this.id = id;
        this.dateTime = dateTime;
        this.name = name;
        this.age = age;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
