package com.sherif.todo.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jackson.jsonpointer.JsonPointerException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.JsonPatchOperation;
import com.github.fge.jsonpatch.ReplaceOperation;
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
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.sherif.todo.AppUtils.EnumResponse.*;

//Todo
// 1- Pagination
// 2- Refactor this method

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
        List<Todo> todos = todoRepository.findAll();

        List<TodoResponse> todoResponseList = todos.stream()
                                                   .map(todo -> {
                                                       TodoResponse todoResponse = new TodoResponse();
                                                       todoResponse.setHeading(todo.getHeading());
                                                       todoResponse.setDescription(todo.getDescription());

                                                       return todoResponse;
                                                   })
                                                   .toList();

        ViewAllTodoResponse viewAllTodoResponse = new ViewAllTodoResponse();
        viewAllTodoResponse.setTodoResponseList(todoResponseList);

        return viewAllTodoResponse;

    }

    @Override
    public TodoResponse findTodoByHeading(String heading) {
        Todo foundTodo = findTodo(heading);
        ModelMapper modelMapper = new ModelMapper();
        TodoResponse newTodo = new TodoResponse();

         modelMapper.map(foundTodo, newTodo);

//        TodoResponse newTodo = TodoResponse.builder()
//                .heading(foundTodo.getHeading())
//                .description(foundTodo.getDescription())
//                .build();

        return newTodo;
    }

    private Todo findTodo(String heading){
        return todoRepository.findByHeading(heading).orElseThrow(()->new TodoNotFound("Todo Cannot Be Found"));
    }

    @Override
    public UpdateTodoResponse updateTodo(UpdateTodoRequest updateTodoRequest, String heading) {

        ObjectMapper mapper = new ObjectMapper();
        Field[] fields = updateTodoRequest.getClass().getDeclaredFields();
        List<ReplaceOperation> operations = Arrays.stream(fields).filter(field->{
            field.setAccessible(true);
            try {
                return field.get(updateTodoRequest) != null;
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).map(field->{
            try {
                JsonPointer jsonPointer = new JsonPointer("/"+field.getName());
                TextNode textNode = new TextNode(field.get(updateTodoRequest).toString());
                ReplaceOperation replaceOperation = new ReplaceOperation(jsonPointer, textNode);
                return replaceOperation;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();

        List<JsonPatchOperation> jsonPatchOperations = new ArrayList<>(operations);

        JsonPatch jsonPatch = new JsonPatch(jsonPatchOperations);

        // Get the TodoNote we want to update
        Todo foundTodo = findTodo(heading);

        JsonNode jsonNode = mapper.convertValue(foundTodo, JsonNode.class);
        try {
            JsonNode updatedNode = jsonPatch.apply(jsonNode);
            Todo updatedTodo = mapper.convertValue(updatedNode, Todo.class);
            todoRepository.save(updatedTodo);
            UpdateTodoResponse response = UpdateTodoResponse.builder()
                                                            .message(UPDATE_SUCCESSFUL.name())
                                                            .build();
            return response;

        } catch (JsonPatchException e) {
            throw new RuntimeException(e);
        }
    }
}
