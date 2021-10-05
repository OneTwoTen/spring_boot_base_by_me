package com.example.ex5_db_springboot.validator;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorModel {
    private String error = "";
    private String[] errors;
    private int code;
	
}
