package com.example.ex5_db_springboot.dao;

import java.util.List;

import com.example.ex5_db_springboot.model.CategoryModel;

import org.springframework.stereotype.Service;

@Service
public interface CategoryDao {

    void insert(CategoryModel model);

    void update(CategoryModel model);

    void delete(Long id);

    CategoryModel findById(Long id);


    List<CategoryModel> findAll();
}
