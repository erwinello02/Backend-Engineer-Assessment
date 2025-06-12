package com.backend.assessment.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private int status;
    public ErrorDetails(String message, int status) {
        this.timestamp = Date.from(Instant.now());
        this.message = message;
        this.status = status;
    }
}