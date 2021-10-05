package com.example.ex5_db_springboot.validator;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

public abstract class CustomError {
    public static ErrorModel getErrorModel(BindingResult errors) {
        ErrorModel errorModel = new ErrorModel();
        if (errors.hasErrors()) {
          
            List<FieldError> fieldErrors = errors.getFieldErrors();
            errorModel.setErrors(fieldErrors.stream().map(e -> {
                return e.getField() + ":" + e.getDefaultMessage();
            }).collect(Collectors.toList()).stream().toArray(String[]::new));
            errorModel.setCode(400);
            return errorModel;
        }
        return null;
    }

}
