package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
public class MealRestController {

    @Autowired
    private final MealService service;

    MealRestController(MealService service) {
        this.service = service;
    }

    public Meal save(Meal meal) {
        return service.save(meal);
    }

    public void delete(int id, int userId) {
        service.delete(id, AuthorizedUser.id());
    }

    public Meal get(int id, int userId) {
        return service.get(id, AuthorizedUser.id());
    }

    public List<MealWithExceed> getAll(int userId, int calories) {
        return service.getAll(AuthorizedUser.id(), calories);
    }

    public List<MealWithExceed> getAll(int userId, LocalDate startDate, LocalDate finishDate, int calories) {
        return service.getAll(userId, startDate, finishDate, calories);
    }

    public List<MealWithExceed> getAll(int userId, LocalTime startTime, LocalTime finishTime, int calories) {
        return service.getAll(userId, startTime, finishTime, calories);
    }

    public List<MealWithExceed> getAll(int userId, LocalDateTime startDateTime, LocalDateTime finishDateTime, int calories) {
        return service.getAll(userId, startDateTime, finishDateTime, calories);
    }

}
