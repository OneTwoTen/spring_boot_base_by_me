package com.example.ex5_db_springboot.dao.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.example.ex5_db_springboot.dao.CategoryDao;
import com.example.ex5_db_springboot.entity.CategoryEntity;

import com.example.ex5_db_springboot.model.CategoryModel;
import com.example.ex5_db_springboot.utils.ObjectMapperUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;

@Component
public class CategoryDaoImpl implements CategoryDao {

    private final Logger logger = LoggerFactory.getLogger(CategoryDaoImpl.class);
    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapperUtils objectMapperUtils;
    private final Validator validator;

    @Autowired
    public CategoryDaoImpl(ObjectMapperUtils objectMapperUtils, Validator validator, JdbcTemplate jdbcTemplate) {
        this.objectMapperUtils = objectMapperUtils;
        this.validator = validator;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(@Valid CategoryModel model) {
        validate(model);
        try {
            String sql = "INSERT INTO category (code, name, description, created_date, updated_date) VALUES (?, ?, ?,?,?)";
            CategoryEntity category = objectMapperUtils.convertEntityAndDTO(model, CategoryEntity.class);
            jdbcTemplate.update(sql, category.getCode(), category.getName(), category.getDescription(),
                    new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        } catch (Exception e) {
            logger.error("Exception: {}", e.getMessage());
        }
    }


    private void validate(@Valid CategoryModel model) {
        Set<ConstraintViolation<CategoryModel>> violations = validator.validate(model);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<CategoryModel> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }
            throw new ConstraintViolationException("Error occurred: " + sb.toString(), violations);
        }
    }

    @Override
    public void update(@Valid CategoryModel model) {
        validate(model);
        try {
            String sql = "UPDATE category SET name = ?, code = ?, description = ?, updated_date =? WHERE id = ?";
            CategoryEntity category = objectMapperUtils.convertEntityAndDTO(model, CategoryEntity.class);
            jdbcTemplate.update(sql, category.getName(), category.getCode(), category.getDescription(),
                    new Timestamp(System.currentTimeMillis()), category.getId());
        } catch (Exception e) {
            logger.error("Error occurred: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM category WHERE id = ?";
            jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            logger.error("Error occurred when delete category with id: " + id, e);
        }
    }

    // @Override
    // public CategoryEntity findById(Long id) {
    // String sql = "SELECT * FROM category WHERE id = ?";
    // System.out.println(jdbcTemplate.queryForObject(sql, new CategoryRowMapper(),
    // id));
    // return jdbcTemplate.queryForObject(sql, new Object[]{id}, new
    // CategoryRowMapper());
    // }

    // @Override
    // public CategoryEntity findById(Long id) {
    // String sql = "SELECT * FROM category WHERE id = ?";
    // var rowMapper = BeanPropertyRowMapper.newInstance(CategoryEntity.class);
    // System.out.println(jdbcTemplate.queryForObject(sql, new CategoryRowMapper(),
    // id));
    // return jdbcTemplate.queryForObject(sql, new Object[] { id }, rowMapper);
    // }
    @Override
    public CategoryModel findById(Long id) {
        try {
            String sql = "SELECT * FROM category WHERE id = ?";
            var rowMapper = BeanPropertyRowMapper.newInstance(CategoryEntity.class);
            return objectMapperUtils.convertEntityAndDTO(jdbcTemplate.queryForObject(sql, rowMapper, id),
                    CategoryModel.class);
        } catch (Exception e) {
            logger.error("Exception: {}", e.toString());
            return null;
        }

    }

    @Override
    public List<CategoryModel> findAll() {
       try {
        String sql = "SELECT id, created_date, updated_date, code, name, description FROM category";
        var rowMapper = BeanPropertyRowMapper.newInstance(CategoryEntity.class);
        List<CategoryEntity> categoryEntityList = jdbcTemplate.query(sql, rowMapper);
        for (CategoryEntity c : categoryEntityList) {
            logger.info("category: {}", c);
        }
        List<CategoryModel> list = objectMapperUtils.mapAll(jdbcTemplate.query(sql, rowMapper), CategoryModel.class);
        for (CategoryModel c : list) {
            logger.info("category: {}", c);
        }
        return list;
       } catch (Exception e) {
           logger.error("Exception: {}", e.toString());
           return null;
       }
    }
}


