package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Meal extends BaseEntity {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    /** temporary constructor for tests */
    // TODO: 07.08.17 delete constructor when task will be completed
    public Meal() {
        this.id = 100000;
        this.dateTime = LocalDateTime.now();
        this.description = "";
        this.calories = 100;
    }

    public Meal(Meal m) {
        this(m.getId(), m.getDateTime(), m.getDescription(), m.getCalories());
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, String description, LocalDateTime dateTime, int calories) {
        super(id);
        this.description = description;
        this.dateTime = dateTime;
        this.calories = calories;
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
