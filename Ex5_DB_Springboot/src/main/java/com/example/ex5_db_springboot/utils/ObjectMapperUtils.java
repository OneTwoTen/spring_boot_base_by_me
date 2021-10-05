package com.example.ex5_db_springboot.utils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjectMapperUtils {
    private final ModelMapper mapper;

    @Autowired
    public ObjectMapperUtils(ModelMapper mapper) {
        this.mapper = mapper;
    }
    
    public <T, E> T convertEntityAndDTO(final E entity, Class<T> dtoClass) {
        return mapper.map(entity, dtoClass);
    }

    public <T, E> List<T> mapAll(final Collection<? extends E> input, final Class<T> dtoClass) {
        return input.stream().map(entity -> convertEntityAndDTO(entity, dtoClass)).collect(Collectors.toList());
    }
}
