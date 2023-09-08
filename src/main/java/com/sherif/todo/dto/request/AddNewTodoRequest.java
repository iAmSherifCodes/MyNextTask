package com.sherif.todo.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddNewTodoRequest {
    private String heading;
    private String description;

}
