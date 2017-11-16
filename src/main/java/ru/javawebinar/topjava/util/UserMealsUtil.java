package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.TimeUtil.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMealWithExceed> filtered = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        System.out.println(filtered);
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        return mealList
                .stream()
                .filter(meal -> isBetween(toLocalTime(meal.getDateTime()), startTime, endTime))
                .map(meal -> new UserMealWithExceed(meal,
                        getCalories(mealList).get(toLocalDate(meal.getDateTime())).intValue() > caloriesPerDay))
                .collect(Collectors.toList());
    }

    public static Map<LocalDate, Integer> getCalories(List<UserMeal> mealList) {
        Map<LocalDate, Integer> caloriesMap = new HashMap<>();
        Integer cal;

        for (UserMeal meal : mealList) {
            cal = caloriesMap.containsKey(toLocalDate(meal.getDateTime())) ?
                    caloriesMap.get(toLocalDate(meal.getDateTime())) + meal.getCalories() : meal.getCalories();
            caloriesMap.put(toLocalDate(meal.getDateTime()), cal);
        }

        return caloriesMap;
    }

}
