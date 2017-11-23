package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private final MealRepository repository;

    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal save(Meal meal) {
        return repository.save(meal);
    }

    @Override
    public void delete(int id, int userId) {
        repository.delete(id, userId);
    }

    @Override
    public Meal get(int id, int userId) {
        return repository.get(id, userId);
    }

    @Override
    public List<MealWithExceed> getAll(int userId, int calories) {
        return MealsUtil.getWithExceeded(repository.getAll(userId), calories);
    }

    @Override
    public List<MealWithExceed> getAll(int userId, LocalDate startDate, LocalDate finishDate, int calories) {
        return MealsUtil.getFilteredWithExceeded(repository.getAll(userId), startDate, finishDate, calories);
    }

    @Override
    public List<MealWithExceed> getAll(int userId, LocalTime startTime, LocalTime finishTime, int calories) {
        return MealsUtil.getFilteredWithExceeded(repository.getAll(userId), startTime, finishTime, calories);
    }

    @Override
    public List<MealWithExceed> getAll(int userId, LocalDateTime startDateTime, LocalDateTime finishDateTime, int calories) {
        return MealsUtil.getFilteredWithExceeded(repository.getAll(userId), startDateTime, finishDateTime, calories);
    }
}