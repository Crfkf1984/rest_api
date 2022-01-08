package org.example.exeptions;

public class TodoNullExeption extends RuntimeException{
    public TodoNullExeption(String message) {
        super(message);
    }
}
