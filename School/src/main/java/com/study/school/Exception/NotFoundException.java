package com.study.school.Exception;

import lombok.Data;

@Data
public class NotFoundException extends RuntimeException{
    private String message;
    public NotFoundException(String message) {
        super();
        this.message=message;
    }
}
