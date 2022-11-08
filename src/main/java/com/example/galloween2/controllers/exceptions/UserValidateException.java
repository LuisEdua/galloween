package com.example.galloween2.controllers.exceptions;

public class UserValidateException extends  RuntimeException{
    public UserValidateException(String message) {
        super(message);
    }
}
