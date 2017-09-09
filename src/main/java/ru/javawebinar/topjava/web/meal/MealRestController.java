package ru.javawebinar.topjava.web.meal;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;

@RestController
@RequestMapping(MealRestController.REST_URL)
public class MealRestController extends AbstractMealController {
    static final String REST_URL = "/rest/meal";

    // TODO: 09.09.17 Вернуть user в JSON-ответе
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Meal get(@RequestParam ("id") int id) {
        return super.get(id);
    }
}