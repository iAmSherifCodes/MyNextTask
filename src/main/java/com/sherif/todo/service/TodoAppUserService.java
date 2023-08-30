package com.sherif.todo.service;

import com.sherif.todo.data.model.User;
import com.sherif.todo.data.repository.UserRepository;
import com.sherif.todo.dto.request.AddNewTodoRequest;
import com.sherif.todo.dto.request.RegistrationRequest;
import com.sherif.todo.dto.request.UpdateTodoRequest;
import com.sherif.todo.dto.response.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class TodoAppUserService implements UserService{
    private final UserRepository userRepository;
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


        return null;
    }

    @Override
    public AddNewTodoResponse addNewTodo(AddNewTodoRequest addNewTodoRequest) {
        return null;
    }

    @Override
    public ViewAllTodoResponse viewAllTodo() {
        return null;
    }

    @Override
    public TodoResponse findTodoByTopic(Long id) {
        return null;
    }

    @Override
    public UpdateTodoResponse updateTodo(UpdateTodoRequest updateTodoRequest) {
        return null;
    }
}
