package com.example.ex5_db_springboot.repository;

import com.example.ex5_db_springboot.entity.ProductEntity;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<ProductEntity> {

    void deleteByCategoryId(Long id);

    void deleteByStorageId(Long id);

}
