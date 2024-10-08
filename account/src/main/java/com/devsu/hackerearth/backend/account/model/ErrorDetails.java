package com.devsu.hackerearth.backend.account.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDetails {
    private int statusCode;
    private String message;
    private String errorCode;
    private String categoryMessage;
    private String action;
    private String details;
}
