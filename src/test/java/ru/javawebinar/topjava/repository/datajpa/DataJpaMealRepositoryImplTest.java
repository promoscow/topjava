package ru.javawebinar.topjava.repository.datajpa;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * Created by promoscow on 14.08.17.
 */
public class DataJpaMealRepositoryImplTest {

    @Autowired
    private CrudMealRepository crudRepository;

    @Test
    public void testSave() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testGet() throws Exception {
        crudRepository.getOneByIdAndUserId(MEAL1_ID, USER_ID);
    }

    @Test
    public void testGetAll() throws Exception {

    }

    @Test
    public void testGetBetween() throws Exception {
        crudRepository.findAllByUserIdOrderByDateTimeDesc(USER_ID);
    }
}