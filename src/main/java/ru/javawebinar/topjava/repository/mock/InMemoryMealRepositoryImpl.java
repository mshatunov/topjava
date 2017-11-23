package ru.javawebinar.topjava.repository.mock;

import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public void delete(int id, int userId) {
        if (repository.get(id).getUserId().equals(userId)) {
            repository.remove(id);
        }
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal = repository.get(id);
        return meal.getUserId().equals(userId) ? meal : null;
    }

    @Override
    public Collection<Meal> getAll() {
        List<Meal> meals = Arrays.asList((Meal) repository.values());
        meals.sort(Comparator.comparing(Meal::getDateTime));
        return meals;

    }
}

