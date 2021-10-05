package com.example.ex5_db_springboot.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.ex5_db_springboot.entity.StorageEntity;
import com.example.ex5_db_springboot.repository.ProductRepository;
import com.example.ex5_db_springboot.repository.StorageRepository;
import com.example.ex5_db_springboot.service.StorageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class StorageServiceImpl extends BaseServiceImpl<StorageEntity, StorageRepository> implements StorageService {
    private final ProductRepository productRepository;
    Logger logger = LoggerFactory.getLogger(StorageServiceImpl.class);
    public StorageServiceImpl(StorageRepository jpaRepository, ProductRepository productRepository) {
        super(jpaRepository);
        this.productRepository = productRepository;
    }
    @Override
    public boolean delete(Long id) {
        try {
            productRepository.deleteByStorageId(id);
            return super.delete(id);
        } catch (Exception e) {
           TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
           logger.error("Failed to delete storage: {}", e.getMessage());
           return false;
        }
    }
  
}
