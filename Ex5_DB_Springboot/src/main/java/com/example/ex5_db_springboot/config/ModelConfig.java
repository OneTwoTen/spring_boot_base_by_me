package com.example.ex5_db_springboot.config;

import com.example.ex5_db_springboot.model.CategoryModel;
import com.example.ex5_db_springboot.model.ProductModel;
import com.example.ex5_db_springboot.model.StorageModel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConfig {

    @Bean
    public ProductModel productModel() {
        return new ProductModel();
    }

    @Bean
    public CategoryModel categoryModel() {
        return new CategoryModel();
    }

    @Bean
    public StorageModel storageModel() {
        return new StorageModel();
    }
}
