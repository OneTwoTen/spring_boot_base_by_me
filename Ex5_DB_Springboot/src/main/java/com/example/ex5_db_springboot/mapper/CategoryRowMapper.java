package com.example.ex5_db_springboot.mapper;

import com.example.ex5_db_springboot.entity.CategoryEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

public class CategoryRowMapper implements RowMapper<CategoryEntity> {

    @Override
    public CategoryEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(resultSet.getLong("id"));
        categoryEntity.setCode(resultSet.getString("code"));
        categoryEntity.setName(resultSet.getString("name"));
        categoryEntity.setDescription(resultSet.getString("description"));
        // categoryEntity.setCreatedDate(Instant.ofEpochMilli(resultSet.getLong("created_date")));
        categoryEntity.setUpdatedDate(Instant.ofEpochMilli(resultSet.getLong("updated_date")));
        return categoryEntity;
    }
}
