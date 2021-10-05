package com.example.ex5_db_springboot.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Long> {

    List<T> findAllByNameContaining(String name, Pageable pageable); 

    Long countByNameContaining(String name);
}
