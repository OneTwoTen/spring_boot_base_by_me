package com.example.ex5_db_springboot.service.impl;

import java.util.List;

import com.example.ex5_db_springboot.entity.CategoryEntity;
import com.example.ex5_db_springboot.repository.CategoryRepository;
import com.example.ex5_db_springboot.repository.ProductRepository;
import com.example.ex5_db_springboot.service.CategoryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<CategoryEntity, CategoryRepository> implements CategoryService {

    private final ProductRepository productRepository;
    private final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    public CategoryServiceImpl(CategoryRepository jpaRepository, ProductRepository productRepository) {
        super(jpaRepository);
        this.productRepository = productRepository;
    }

    @Override
    public boolean delete(Long id) {
        try {
            productRepository.deleteByCategoryId(id);
            return super.delete(id);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Error deleting: {}", e.getMessage());
            return false;
        }
    }

}
