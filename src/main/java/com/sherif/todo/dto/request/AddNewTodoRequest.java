package com.sherif.todo.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AddNewTodoRequest {
    private String heading;
    private String description;

}
