package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MealsUtil {

    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static List<MealWithExceed> getWithExceeded(List<Meal> meals, int caloriesPerDay) {
        return getFilteredWithExceeded(meals, LocalTime.MIN, LocalTime.MAX, caloriesPerDay);
    }

    public static List<MealWithExceed> getFilteredWithExceeded(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        return getFilteredWithExceededAbstract(meals, caloriesPerDay, meal -> DateTimeUtil.isBetween(meal.getTime(), startTime, endTime));
    }

    public static List<MealWithExceed> getFilteredWithExceeded(List<Meal> meals, LocalDate startDate, LocalDate finishDate, int caloriesPerDay) {
        return getFilteredWithExceededAbstract(meals, caloriesPerDay, meal -> DateTimeUtil.isBetween(meal.getDate(), startDate, finishDate));
    }

    public static List<MealWithExceed> getFilteredWithExceeded(List<Meal> meals, LocalDateTime startDateTime, LocalDateTime finishDateTime, int caloriesPerDay) {
        return getFilteredWithExceededAbstract(meals, caloriesPerDay, meal -> DateTimeUtil.isBetween(meal.getDateTime(), startDateTime, finishDateTime));
    }

    private static List<MealWithExceed> getFilteredWithExceededAbstract(Collection<Meal> meals, int caloriesPerDay, Predicate<Meal> mealPredicate) {
        return meals.stream()
                .filter(mealPredicate)
                .map(meal -> createWithExceed(meal, caloriesSumByDate(meals).get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    private static Map<LocalDate, Integer> caloriesSumByDate(Collection<Meal> meals) {
        return meals
                .stream()
                .collect(Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories)));
    }

    private static MealWithExceed createWithExceed(Meal meal, boolean exceeded) {
        return new MealWithExceed(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceeded);
    }

}