package ru.javawebinar.topjava.model;

/**
 * Класс Bot, экземпляры которого (объекты) мы будем создавать.
 * <p>
 * Created by promoscow on 26.07.17.
 */
public class Bot {
    private Integer id;
    private String name;
    private String serial;

    public Bot(String name, String serial, Integer id) {
        this.name = name;
        this.serial = serial;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
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
