package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {
    private static final Logger log = LoggerFactory.getLogger(MealRestController.class);

    private final MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    @RequestMapping(value = "/get/{id}")
    public String get(@PathVariable("id") int id, Model model) {
        int userId = AuthorizedUser.id();
        log.info("get meal {} for userId={}", id, userId);
        Meal meal = service.get(id, userId);
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    public void delete(int id) {
        int userId = AuthorizedUser.id();
        log.info("delete meal {} for userId={}", id, userId);
        service.delete(id, userId);
    }

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public String getAll(Model model) {

        int userId = AuthorizedUser.id();
        log.info("getAll for userId={}", userId);
        List<MealWithExceed> list = MealsUtil.getWithExceeded(service.getAll(userId), AuthorizedUser.getCaloriesPerDay());
        list.forEach(System.out::println);
        model.addAttribute("meals", list);
        return "meals";
    }

    public Meal create(Meal meal) {
        int userId = AuthorizedUser.id();
        log.info("create {} for userId={}", meal, userId);
        checkNew(meal);
        return service.create(meal, userId);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(HttpServletRequest request) {

        System.out.println("***** UPDATE *****");
        System.out.println("***** UPDATE *****");
        System.out.println("***** UPDATE *****");
        System.out.println("***** UPDATE *****");
        System.out.println("***** UPDATE *****");
        int userId = AuthorizedUser.id();
        int id = Integer.parseInt(request.getParameter("id"));

        Meal meal = new Meal();
        meal.setId(id);
        meal.setUser(new User());
        meal.setCalories(Integer.parseInt(request.getParameter("calories")));
        meal.setDescription(request.getParameter("description"));
        meal.setDateTime(LocalDateTime.parse(request.getParameter("dateTime")));

        System.out.println(meal);
        log.info("update {} with id={} for userId={}", meal, id, userId);
        assureIdConsistent(meal, id);
        service.update(meal, userId);
        return "meals";
    }

    /**
     * <ol>Filter separately
     *   <li>by date</li>
     *   <li>by time for every date</li>
     * </ol>
     */
    public List<MealWithExceed> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        int userId = AuthorizedUser.id();
        log.info("getBetween dates({} - {}) time({} - {}) for userId={}", startDate, endDate, startTime, endTime, userId);

        return MealsUtil.getFilteredWithExceeded(
                service.getBetweenDates(
                        startDate != null ? startDate : DateTimeUtil.MIN_DATE,
                        endDate != null ? endDate : DateTimeUtil.MAX_DATE, userId),
                startTime != null ? startTime : LocalTime.MIN,
                endTime != null ? endTime : LocalTime.MAX,
                AuthorizedUser.getCaloriesPerDay()
        );
    }
}
