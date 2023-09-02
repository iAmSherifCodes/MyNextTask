package com.sherif.todo.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateTodoRequest {
    private String heading;
    private String description;
}
