package com.sherif.todo.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TodoResponse {
    private String heading;
    private String description;
}
