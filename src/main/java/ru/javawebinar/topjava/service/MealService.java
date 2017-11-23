package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface MealService {

    Meal save(Meal meal);

    void delete(int id, int userId);

    Meal get(int id, int userId);

    List<MealWithExceed> getAll(int userId, int calories);

    List<MealWithExceed> getAll(int userId, LocalDate startDate, LocalDate finishDate, int calories);

    List<MealWithExceed> getAll(int userId, LocalTime startTime, LocalTime finishTime, int calories);

    List<MealWithExceed> getAll(int userId, LocalDateTime startDateTime, LocalDateTime finishDateTime, int calories);

}