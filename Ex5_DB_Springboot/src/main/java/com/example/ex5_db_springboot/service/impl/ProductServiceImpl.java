package com.example.ex5_db_springboot.service.impl;

import com.example.ex5_db_springboot.entity.ProductEntity;
import com.example.ex5_db_springboot.repository.BaseRepository;
import com.example.ex5_db_springboot.repository.ProductRepository;
import com.example.ex5_db_springboot.service.ProductService;

import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends BaseServiceImpl<ProductEntity, ProductRepository> implements ProductService{

    public ProductServiceImpl(ProductRepository jpaRepository) {
        super(jpaRepository);
    }

   

}
