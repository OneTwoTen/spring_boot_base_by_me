package com.example.ex5_db_springboot.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryModel {
    private Long id;
    private Date createdDate;
    private Date updatedDate;
    @NotNull
    @Length(min = 3, max = 20, message = "Code must be between 3 and 20 characters")
    private String code;
    @NotNull
    @Length(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    private String name;
    @NotNull
    @Length(min = 10, max = 50, message = "Description must be between 10 and 50 characters")
    private String description;
}
