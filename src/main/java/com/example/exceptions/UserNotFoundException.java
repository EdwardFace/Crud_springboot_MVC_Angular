package com.example.exceptions;

public class UserNotFoundException extends RuntimeException{
    UserNotFoundException(String message){
        super(message);
    }
}
