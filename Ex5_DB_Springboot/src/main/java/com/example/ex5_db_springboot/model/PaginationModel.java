package com.example.ex5_db_springboot.model;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PaginationModel {
    private Metadata metadata;
    private List<?> data;
}
