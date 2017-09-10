package ru.javawebinar.topjava.web.meal;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(MealRestController.REST_URL)
public class MealRestController extends AbstractMealController {
    static final String REST_URL = "/rest/meal";

    // TODO: 09.09.17 Вернуть user в JSON-ответе
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Meal get(@RequestParam ("id") int id) {
        final Meal meal = super.get(id);
//        meal.setUser(new User());
//        meal.getUser().setId(AuthorizedUser.id());
        return meal;
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getAll() {
        return super.getAll();
    }

    @Override
    public Meal create(Meal meal) {
        return super.create(meal);
    }

    @Override
    public void update(Meal meal, int id) {
        super.update(meal, id);
    }

    @Override
    public List<MealWithExceed> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        return super.getBetween(startDate, startTime, endDate, endTime);
    }
}