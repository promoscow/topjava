package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DataJpaMealRepositoryImpl implements MealRepository {

    @Autowired
    private CrudMealRepository crudRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    public Meal save(Meal meal, int userId) {
        meal.setUser(crudUserRepository.findOne(userId));
        return crudRepository.save(meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        try {
            crudRepository.delete(get(id, userId));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Meal get(int id, int userId) {
        return crudRepository.getOneByIdAndUserId(id, userId);
    }

    @Override
    public List<Meal> getAll(int userId) {
        System.out.println();
        System.out.println("**********************");
        System.out.println("DATAJPA IMPLEMENTATION");
        System.out.println("**********************");
        System.out.println();
        return crudRepository.getAllByUserId(userId)
                .stream()
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return crudRepository.findAllByUserIdOrderByDateTimeDesc(userId)
                .stream()
                .filter(meal -> (meal.getDateTime().toEpochSecond(ZoneOffset.UTC) > startDate.toEpochSecond(ZoneOffset.UTC))
                        && (meal.getDateTime().toEpochSecond(ZoneOffset.UTC) < endDate.toEpochSecond(ZoneOffset.UTC)))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
