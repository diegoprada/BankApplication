package com.devsu.hackerearth.backend.account.exceptions;

public class BadRequestExceptions extends GeneralException {
    private static final long serialVersionUID = 100L;

    public BadRequestExceptions(String code, String message, String categoryMessage, String action, Integer codeHttp) {
        super(code, message, categoryMessage, action, codeHttp);
    }

}
