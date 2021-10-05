package com.example.ex5_db_springboot.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel{
    private Long id;
    private Date createdDate;
    private Date updatedDate;
    @NotNull(message = "code must not null")
    private String code;
    @NotEmpty(message = "Category can't null")
    private CategoryModel category;
    @NotEmpty(message = "Storage can't null")
    private StorageModel storage;
    @NotNull(message = "Name must not null")
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;
    private int quantitySold;
    private String image;
}
