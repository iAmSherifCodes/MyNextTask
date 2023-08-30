package com.sherif.todo.service;

import com.sherif.todo.dto.request.AddNewTodoRequest;
import com.sherif.todo.dto.request.RegistrationRequest;
import com.sherif.todo.dto.request.UpdateTodoRequest;
import com.sherif.todo.dto.response.*;

public interface UserService {
    RegistrationResponse register (RegistrationRequest request);
    AddNewTodoResponse addNewTodo (AddNewTodoRequest addNewTodoRequest);
    ViewAllTodoResponse viewAllTodo ();
    TodoResponse findTodoByTopic(Long id);
    UpdateTodoResponse updateTodo (UpdateTodoRequest updateTodoRequest);

}
