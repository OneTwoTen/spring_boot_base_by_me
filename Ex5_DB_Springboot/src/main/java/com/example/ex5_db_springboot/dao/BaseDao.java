package com.example.ex5_db_springboot.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BaseDao<T> {
    private final Logger logger = LoggerFactory.getLogger(BaseDao.class);
    private final JdbcTemplate jdbcTemplate;

    public void insert(T obj, String sql) {
        try {
            jdbcTemplate.update("INSERT INTO " + obj.getClass().getName(), obj);
        } catch (Exception e) {
            logger.error("Exception while inserting: {}", e.getMessage());
        }
    }

    public void update(T obj, String sql) {
        try {
            jdbcTemplate.update(sql, obj);
        } catch (Exception e) {
            logger.error("Exception while updating: {}", e.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?", id);
        } catch (Exception e) {
            logger.error("Exception while deleting: {}", e.getMessage());
        }
    }

    public List<T> findAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM " + getTableName(), getBeanPropertyRowMapper());
        } catch (Exception e) {
            logger.error("Exception while finding all: {}", e.getMessage());
            return null;
        }
    }

    public T findById(Long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM " + getTableName() + " WHERE id = ?", getBeanPropertyRowMapper(), id);
        } catch (Exception e) {
            logger.error("Exception while finding by id: {}", e.getMessage());
            return null;
        }
    }

    protected abstract String getTableName();

    protected abstract BeanPropertyRowMapper<T> getBeanPropertyRowMapper();
}

