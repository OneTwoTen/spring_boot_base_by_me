package com.example.ex5_db_springboot.service;

import java.util.List;

import com.example.ex5_db_springboot.model.PaginationModel;

public interface BaseService<T> {


    T create(T t);

    T update(T t);

    boolean delete(Long id);

    PaginationModel findAll(int pageNumber, int pageSize, String sortBy, String sortOrder, String name);

    T findById(Long id);
}