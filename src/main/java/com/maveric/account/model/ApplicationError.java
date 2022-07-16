package com.maveric.account.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@RequiredArgsConstructor
public class ApplicationError {
    private final HttpStatus code;
    private final String message;
}
