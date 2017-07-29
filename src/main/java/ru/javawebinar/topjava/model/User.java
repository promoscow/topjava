package ru.javawebinar.topjava.model;

import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class User extends NamedEntity {

    private String email;

    private String password;

    private boolean enabled = true;

    private Date registered = new Date();

    private Set<Role> roles;

    private int caloriesPerDay = DEFAULT_CALORIES_PER_DAY;

    public User(boolean b, Date date, Role roleUser) {
    }

    public User(String name, String email, String password, Role role, Role... roles) {
        this(name, email, password, DEFAULT_CALORIES_PER_DAY, true, EnumSet.of(role, roles));
    }

    public User(String name, String email, String password, int caloriesPerDay, boolean enabled, Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.caloriesPerDay = caloriesPerDay;
        this.enabled = enabled;
        this.roles = roles;
    }

    public User(String s, String userName, String email, String password, Role roleAdmin) {
        super();
    }

    public User(boolean enabled, Date registered, Set<Role> roles, int caloriesPerDay) {
        this.enabled = enabled;
        this.registered = registered;
        this.roles = roles;
        this.caloriesPerDay = caloriesPerDay;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getCaloriesPerDay() {
        return caloriesPerDay;
    }

    public void setCaloriesPerDay(int caloriesPerDay) {
        this.caloriesPerDay = caloriesPerDay;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User (" +
                "id=" + id +
                ", email=" + email +
                ", name=" + name +
                ", enabled=" + enabled +
                ", roles=" + roles +
                ", caloriesPerDay=" + caloriesPerDay +
                ')';
    }
}