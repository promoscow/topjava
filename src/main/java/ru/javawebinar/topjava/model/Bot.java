package ru.javawebinar.topjava.model;

/**
 * Класс Bot, экземпляры которого (объекты) мы будем создавать.
 *
 * Created by promoscow on 26.07.17.
 */
public class Bot {
    private Integer id;
    private final String name;
    private final Integer serial;

    public Bot(String name, Integer serial) {
        this.name = name;
        this.serial = serial;
    }

    public Bot(String name, Integer serial, Integer id) {
        this.name = name;
        this.serial = serial;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getSerial() {
        return serial;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Bot{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", serial=" + serial +
                '}';
    }
}
