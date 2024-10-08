package com.devsu.hackerearth.backend.account.exceptions;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GeneralException extends Exception {
    private String code;
    private String message;
    private String categoryMessage;
    private String action;
    private Integer codeHttp;
    private String messageId;

    public GeneralException(String code, String message, String categoryMessage, String action, Integer codeHttp) {
        this.code = code;
        this.message = message;
        this.categoryMessage = categoryMessage;
        this.action = action;
        this.codeHttp = codeHttp;
    }
}
