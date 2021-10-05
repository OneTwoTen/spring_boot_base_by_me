package com.example.ex5_db_springboot.mapper;

import com.example.ex5_db_springboot.entity.ProductEntity;
import com.example.ex5_db_springboot.model.ProductModel;
import com.example.ex5_db_springboot.service.ProductService;
import com.example.ex5_db_springboot.utils.ObjectMapperUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final ObjectMapperUtils modelMapperUtils;
    private final ProductService productService;
    private final Logger logger = LoggerFactory.getLogger(ProductMapper.class);

    public ProductModel update(ProductModel productModel) {
        try {
            ProductEntity productEntity = modelMapperUtils.convertEntityAndDTO(productModel, ProductEntity.class);
            productService.update(productEntity);
            return modelMapperUtils.convertEntityAndDTO(productEntity, ProductModel.class);
        } catch (Exception e) {
            logger.error("Error updating: {}", e.getMessage());
            return null;
        }
    }
    public ProductModel create(ProductModel productModel) {
        try {
            ProductEntity productEntity = modelMapperUtils.convertEntityAndDTO(productModel, ProductEntity.class);
            productService.create(productEntity);
            return modelMapperUtils.convertEntityAndDTO(productEntity, ProductModel.class);
        } catch (Exception e) {
            logger.error("Error creating: {}", e.getMessage());
            return null;
        }
    }
}
