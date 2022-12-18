package com.up.usersauth.exceptions;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ExceptionHandler {

    private String message;
    private String details;
}
