package com.example.ex5_db_springboot.service.impl;

import java.util.List;
import java.util.Optional;

import com.example.ex5_db_springboot.model.PaginationModel;
import com.example.ex5_db_springboot.repository.BaseRepository;
import com.example.ex5_db_springboot.service.BaseService;
import com.example.ex5_db_springboot.utils.PaginationModelUtil;
import com.example.ex5_db_springboot.utils.PaginationUtil;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BaseServiceImpl<T, R extends BaseRepository<T>> implements BaseService<T> {

    private final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);
    private final R jpaRepository;
    @Override
    public T create(T t) {
        try {
            return jpaRepository.save(t);
        } catch (Exception e) {
            logger.error("Error creating: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public T update(T t) {
        try {
            return jpaRepository.save(t);
        } catch (Exception e) {
            logger.error("Error updating: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            Optional<T> result = jpaRepository.existsById(id) ? jpaRepository.findById(id) : Optional.empty();
            if (result.isPresent()) {
                jpaRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("Error deleting: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public PaginationModel findAll(int pageNumber, int pageSize, String sortBy, String sortOrder, String name) {
        try {
            logger.info("findAll: pageNumber={}, pageSize={}, sortBy={}, sortOrder={}, name={}", pageNumber, pageSize,
                    sortBy, sortOrder, name);
            Pageable pageable = PaginationUtil.pageable(pageNumber, pageSize, sortBy, sortOrder);
            List<T> result = jpaRepository.findAllByNameContaining(name, pageable);
            Long count = jpaRepository.countByNameContaining(name);
            logger.info("countByNameLike: {}", count);
            return PaginationModelUtil.getPaginationModel(count, pageNumber, pageSize, result);
        } catch (Exception e) {
            logger.error("Error: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public T findById(Long id) {
        try {
            Optional<T> result = jpaRepository.findById(id);
            if (result.isPresent()) {
                return result.get();
            }
            return null;
        } catch (Exception e) {
            logger.error("Error: {}", e.getMessage());
            return null;
        }
    }

}
