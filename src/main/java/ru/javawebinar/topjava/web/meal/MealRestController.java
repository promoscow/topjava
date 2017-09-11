package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(MealRestController.REST_URL)
public class MealRestController extends AbstractMealController {
    static final String REST_URL = "/rest/meal";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Meal get(@RequestParam ("id") int id) {
        final Meal meal = super.get(id);
//        meal.setUser(new User());
//        meal.getUser().setId(AuthorizedUser.id());
        return meal;
    }

    @DeleteMapping(value = "delete")
    public void delete(@RequestParam ("id") int id) {
        super.delete(id);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getAll() {
        return super.getAll();
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Meal create(@RequestBody Meal meal) {
        return super.create(meal);
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Meal meal) {
        super.update(meal, meal.getId());
    }

    @PostMapping(value = "/filter", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public List<MealWithExceed> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        return super.getBetween(startDate, startTime, endDate, endTime);
    }
}