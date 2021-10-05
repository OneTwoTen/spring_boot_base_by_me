package com.example.ex5_db_springboot.mapper;

import com.example.ex5_db_springboot.entity.StorageEntity;
import com.example.ex5_db_springboot.model.StorageModel;
import com.example.ex5_db_springboot.service.StorageService;
import com.example.ex5_db_springboot.utils.ObjectMapperUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class StorageMapper {
    private final ObjectMapperUtils objectMapperUtils;
    private final Logger logger = LoggerFactory.getLogger(ProductMapper.class);
    private final StorageService storageService;

    public StorageModel create(StorageModel storageModel) {
        try {
            StorageEntity storageEntity = objectMapperUtils.convertEntityAndDTO(storageModel, StorageEntity.class);
            storageService.create(storageEntity);
            return objectMapperUtils.convertEntityAndDTO(storageEntity, StorageModel.class);
        } catch (Exception e) {
            logger.error("Error: {}", e.getMessage());
            return null;
        }
    }

    public StorageModel update(StorageModel storageModel) {
        try {
            StorageEntity storageEntity = objectMapperUtils.convertEntityAndDTO(storageModel, StorageEntity.class);
            storageService.update(storageEntity);
            return objectMapperUtils.convertEntityAndDTO(storageEntity, StorageModel.class);
        } catch (Exception e) {
            logger.error("Error: {}", e.getMessage());
            return null;
        }
    }
}
