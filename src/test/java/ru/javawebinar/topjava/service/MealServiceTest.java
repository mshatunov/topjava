package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.topjava.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;
    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() {
        dbPopulator.execute();
    }

    @Test
    public void get() {
        Meal meal = new Meal(LocalDateTime.now(), "desc", 333);
        Meal created = service.save(meal, ADMIN_ID);
        MATCHER_MEAL.assertEquals(meal, created);
    }

    @Test(expected = NotFoundException.class)
    public void delete() {
        Meal created = service.save(new Meal(LocalDateTime.now(), "desc", 333), ADMIN_ID);
        service.delete(created.getId(), ADMIN_ID);
        service.get(created.getId(), ADMIN_ID);
    }

    @Test
    public void getAll() {
        Meal meal = service.get(5, ADMIN_ID);
        Meal meal2 = service.get(6, ADMIN_ID);
        Meal meal3 = service.get(7, ADMIN_ID);
        List<Meal> meals = service.getAll(ADMIN_ID);
        MATCHER_MEAL.assertCollectionEquals(Arrays.asList(meal, meal2, meal3), meals);
    }

    @Test
    public void update() {
        Meal meal = new Meal(LocalDateTime.now(), "desc", 333);
        service.save(meal, ADMIN_ID);
        meal.setCalories(777);
        Meal created = service.update(meal, ADMIN_ID);
        MATCHER_MEAL.assertEquals(meal, created);
    }

    @Test
    public void save() {
        Meal meal = new Meal(LocalDateTime.now(), "desc", 333);
        Meal created = service.save(meal, ADMIN_ID);
        MATCHER_MEAL.assertEquals(meal, created);
    }
}