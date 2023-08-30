package com.sherif.todo.dto.response;

import com.sherif.todo.dto.response.TodoResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ViewAllTodoResponse {
    private List<TodoResponse> todoResponseList;
}
