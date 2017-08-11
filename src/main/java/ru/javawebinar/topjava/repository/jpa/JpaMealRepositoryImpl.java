package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional(readOnly = false)
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            System.out.println("persist: " + meal);
            meal.setUser(em.find(User.class, userId));
            em.persist(meal);
            return meal;
        } else {
            System.out.println("merge: " + meal);
            meal.setUser(em.find(User.class, userId));
            return em.merge(meal);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.find(Meal.class, id).getUser().getId() == userId
                && em.createNamedQuery(Meal.DELETE).setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        return em.find(Meal.class, id).getUser().getId() == userId ? em.find(Meal.class, id) : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL_SORTED, Meal.class)
                .getResultList()
                .stream()
                .filter(meal -> meal.getUser().getId() == userId)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createNamedQuery(Meal.ALL_SORTED, Meal.class)
                .getResultList()
                .stream()
                .filter(meal -> (meal.getUser().getId() == userId)
                        && (meal.getDateTime().toEpochSecond(ZoneOffset.UTC) > startDate.toEpochSecond(ZoneOffset.UTC))
                        && (meal.getDateTime().toEpochSecond(ZoneOffset.UTC) < endDate.toEpochSecond(ZoneOffset.UTC)))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}