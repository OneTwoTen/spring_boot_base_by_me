package com.example.ex5_db_springboot.mapper;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Set;

import com.example.ex5_db_springboot.entity.CategoryEntity;
import com.example.ex5_db_springboot.model.CategoryModel;
import com.example.ex5_db_springboot.service.CategoryService;
import com.example.ex5_db_springboot.utils.ObjectMapperUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CategoryMapper {
    private final ObjectMapperUtils modelMapperUtils;
    private final CategoryService categoryService;
    private final Logger logger = LoggerFactory.getLogger(CategoryMapper.class);

    public CategoryModel update(CategoryModel categoryModel) {
        try {
            CategoryEntity categoryEntity = modelMapperUtils.convertEntityAndDTO(categoryModel, CategoryEntity.class);
            categoryEntity.setUpdatedDate(Instant.now());
            categoryService.update(categoryEntity);
            return modelMapperUtils.convertEntityAndDTO(categoryEntity, CategoryModel.class);
        } catch (Exception e) {
            logger.error("Error updating: {}", e.getMessage());
            return null;
        }
    }
    
    public CategoryModel create(CategoryModel categoryModel) {
        try {
            Set<String> zones = ZoneId.getAvailableZoneIds();
            ZoneId zoneId = ZoneId.of(zones.toArray()[0].toString());
            CategoryEntity categoryEntity = modelMapperUtils.convertEntityAndDTO(categoryModel, CategoryEntity.class);
            categoryEntity.setUpdatedDate(Instant.now());
            categoryEntity.setCreatedDate(Instant.now());
            categoryService.create(categoryEntity);
            return modelMapperUtils.convertEntityAndDTO(categoryEntity, CategoryModel.class);
        } catch (Exception e) {
            logger.error("Error creating: {}", e.getMessage());
            return null;
        }
    }
}
