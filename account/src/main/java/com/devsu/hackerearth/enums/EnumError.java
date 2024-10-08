package com.devsu.hackerearth.enums;

public enum EnumError {
    DENEGADA_AUTORIZADOR(1001, "Denegada Autorizador"),
    EMAIL_EXISTS(1002, "El correo ya registrado"),
    INVALID_EMAIL_FORMAT(1003, "Formato de correo inválido"),

    INVALID_PASSWORD_FORMAT(1004,
            "Formato de contraseña inválido,  Esta clave debe contener al menos un número (12345), al menos una letra (abc), y una longitud de 8 caracteres"),
    USER_NO_FOUND(1005, "Usuario no encontrado"),
    INVALID_PASSWORD(1006, "Contraseña incorrecta"),

    ERROR_BD(1007, "Error BD "),

    ERROR_DE_FORMATO(1008, "Error de formato"),
    SERVICIO_NO_DISPONIBLE(1009, "Servicio no disponible. Intente más tarde");

    private final int code;
    private final String message;

    EnumError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getCodeString() {
        return String.valueOf(code);
    }

    public String getMessage() {
        return message;
    }
}
