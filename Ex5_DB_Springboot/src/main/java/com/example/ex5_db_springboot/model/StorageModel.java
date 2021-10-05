package com.example.ex5_db_springboot.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Date;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StorageModel {
    private Long id;
    private Date createdDate;
    private Date updatedDate;
    @Length(min = 6, message = "name min length is 6")
    @Length(max = 25, message = "name max length is 25")
    private String name;
    @Length(min = 6, message = "address min length is 6")
    @Length(max = 25, message = "address max length is 255")
    private String address;

}
