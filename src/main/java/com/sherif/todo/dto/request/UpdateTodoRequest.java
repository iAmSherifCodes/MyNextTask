package com.sherif.todo.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class UpdateTodoRequest {
    private String heading;
    private String description;
}
