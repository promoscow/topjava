package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Repository
public class JdbcMealRepositoryImpl implements MealRepository {

//    private static final BeanPropertyRowMapper<Meal> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Meal.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertMeal;

    @Autowired
    public JdbcMealRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertMeal = new SimpleJdbcInsert(dataSource)
                .withTableName("meals")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Meal save(Meal meal, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", meal.getId())
                .addValue("description", meal.getDescription())
                .addValue("datetime", meal.getDateTime())
                .addValue("calories", meal.getCalories())
                .addValue("userid", userId);

        if (meal.isNew()) {
            Number newKey = insertMeal.executeAndReturnKey(map);
            meal.setId(newKey.intValue());
        } else {
            namedParameterJdbcTemplate.update(
                    "UPDATE meals SET description=:description, datetime=:datetime, calories=:calories WHERE id=:id", map
            );
        }
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM meals WHERE id = ? AND userid = ?", id, userId) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        return jdbcTemplate.queryForObject("SELECT * FROM meals WHERE id = ? AND userid = ?", (rs, rowNum) -> {
            return new Meal(rs.getInt("id"), rs.getString("description"), LocalDateTime
                    .ofInstant(rs.getTimestamp("datetime").toInstant(), TimeZone.getDefault().toZoneId()), rs.getInt("calories"));
        }, id, userId);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return jdbcTemplate.query("SELECT * FROM meals WHERE userid = ?", (rs, rowNum) -> {
            return new Meal(rs.getInt("id"), rs.getString("description"), rs.getTimestamp("datetime").toLocalDateTime(), rs.getInt("calories"));
        }, AuthorizedUser.id())
                .stream().sorted(Comparator.comparing(Meal::getDateTime)).collect(Collectors.toList());
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return jdbcTemplate.query("SELECT * FROM meals WHERE userid = ? AND datetime > ? AND meals.datetime < ?", (rs, rowNum) -> {
        return new Meal(rs.getInt("id"), rs.getString("description"), rs.getTimestamp("datetime").toLocalDateTime(), rs.getInt("calories"));
    }, AuthorizedUser.id(), startDate, endDate)
                .stream().sorted(Comparator.comparing(Meal::getDateTime)).collect(Collectors.toList());
    }
}
