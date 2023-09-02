package com.sherif.todo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sherif.todo.data.model.Todo;
import com.sherif.todo.data.model.User;
import com.sherif.todo.data.repository.TodoRepository;
import com.sherif.todo.data.repository.UserRepository;
import com.sherif.todo.dto.request.AddNewTodoRequest;
import com.sherif.todo.dto.request.RegistrationRequest;
import com.sherif.todo.dto.request.UpdateTodoRequest;
import com.sherif.todo.dto.response.*;
import com.sherif.todo.exceptions.TodoNotFound;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.web.JsonPath;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sherif.todo.AppUtils.EnumResponse.REGISTRATION_SUCCESSFUL;
import static com.sherif.todo.AppUtils.EnumResponse.TASK_ADDED;

@Service @AllArgsConstructor @Slf4j
public class TodoAppUserService implements UserService{

    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    @Override
    public RegistrationResponse register(RegistrationRequest request) {
        String username = request.getName();
        String email = request.getEmail();
        String password = request.getPassword();

        User user = User
                .builder()
                .name(username)
                .email(email)
                .password(password)
                .build();

        userRepository.save(user);

        RegistrationResponse response = new RegistrationResponse();
        response.setMessage(REGISTRATION_SUCCESSFUL.name());

        return response;
    }

    @Override
    public AddNewTodoResponse addNewTodo(AddNewTodoRequest addNewTodoRequest) {
        String heading = addNewTodoRequest.getHeading();
        String description = addNewTodoRequest.getDescription();

        Todo todo = Todo.builder()
                        .heading(heading)
                        .description(description)
                        .build();


        todoRepository.save(todo);

        AddNewTodoResponse response = new AddNewTodoResponse();
        response.setMessage(TASK_ADDED.name());
        return response;
    }

    @Override
    public ViewAllTodoResponse viewAllTodo() {

        //Todo
        // 1- Pagination
        List<Todo> todos = todoRepository.findAll();

        List<TodoResponse> todoResponseList = todos.stream()
                                                   .map(todo -> TodoResponse
                                                   .builder()
                                                   .heading(todo.getHeading())
                                                   .description(todo.getDescription())
                                                   .build())
                                                   .toList();

        ViewAllTodoResponse viewAllTodoResponse = new ViewAllTodoResponse();
        viewAllTodoResponse.setTodoResponseList(todoResponseList);

        return viewAllTodoResponse;

    }

    @Override
    public TodoResponse findTodoByHeading(String heading) {
        Todo foundTodo = todoRepository.findByHeading(heading).orElseThrow(()->new TodoNotFound("Todo Cannot Be Found"));

        TodoResponse newTodo = TodoResponse.builder()
                .heading(foundTodo.getHeading())
                .description(foundTodo.getDescription())
                .build();

        return newTodo;
    }

    @Override
    public UpdateTodoResponse updateTodo(UpdateTodoRequest updateTodoRequest) {

        return null;
    }
}
