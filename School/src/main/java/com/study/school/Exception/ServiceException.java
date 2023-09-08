package com.study.school.Exception;

import lombok.Data;

@Data
public class ServiceException extends RuntimeException{
    private String message;
    public ServiceException(String message) {
        super();
        this.message=message;
    }
}
