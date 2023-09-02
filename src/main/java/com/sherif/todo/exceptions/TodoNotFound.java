package com.sherif.todo.exceptions;




public class TodoNotFound extends RuntimeException {
    public TodoNotFound(String message){
        super(message);
    }
}
