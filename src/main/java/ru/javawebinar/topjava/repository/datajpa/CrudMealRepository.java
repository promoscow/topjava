package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    List<Meal> findAll(int userId);

    Meal getOne(int id, int userId);
}
