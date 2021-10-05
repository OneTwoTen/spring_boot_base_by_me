package com.example.ex5_db_springboot.mapper;

import com.example.ex5_db_springboot.entity.CategoryEntity;
import com.example.ex5_db_springboot.entity.ProductEntity;
import com.example.ex5_db_springboot.entity.StorageEntity;
import lombok.SneakyThrows;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;

@Component
public class ProductRowMapper implements RowMapper<ProductEntity> {
    private final Logger logger = LoggerFactory.getLogger(ProductRowMapper.class);

    @SneakyThrows
    @Override
    public ProductEntity mapRow(ResultSet resultSet, int i) {
        ProductEntity productEntity = BeanPropertyRowMapper.newInstance(ProductEntity.class).mapRow(resultSet, i);
        CategoryEntity categoryEntity = BeanPropertyRowMapper.newInstance(CategoryEntity.class).mapRow(resultSet, i);
        logger.info("categoryEntity: {}", categoryEntity);
        StorageEntity storageEntity = BeanPropertyRowMapper.newInstance(StorageEntity.class).mapRow(resultSet, i);
        logger.info("storageEntity: {}", storageEntity);
        assert productEntity != null;
        productEntity.setCategory(categoryEntity);
        productEntity.setStorage(storageEntity);
        logger.info("productEntity: {}", productEntity);
        return productEntity;
    }
}
