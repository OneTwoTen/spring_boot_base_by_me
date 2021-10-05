package com.example.ex5_db_springboot.model;


import lombok.*;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseModel implements Serializable {
    private Long id;
    private Date createdDate;
    private Date updatedDate;

}
