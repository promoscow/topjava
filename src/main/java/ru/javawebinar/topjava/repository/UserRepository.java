package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.User;

import java.util.Collection;

/**
 * Created by promoscow on 24.07.17.
 */
public interface UserRepository {
    User save(User user);
    void delete(int id);
    User get(int id);
    Collection<User> getAll();
}
