package com.sherif.todo.service;

import com.sherif.todo.dto.request.AddNewTodoRequest;
import com.sherif.todo.dto.request.RegistrationRequest;
import com.sherif.todo.dto.response.AddNewTodoResponse;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class TodoAppUserServiceTest {
    @Autowired
    TodoAppUserService userService;



    @Test
    void register() {
        RegistrationRequest request = new RegistrationRequest();
        request.setName("Oluchi");
        request.setEmail("oluchitest@yahoo.com");
        request.setPassword("pass1");

        var response = userService.register(request);
        assertThat(response).isNotNull();

    }

    @Test
    void addNewTodo() {
        AddNewTodoRequest request = AddNewTodoRequest.builder()
                .heading("My First Task")
                .description("Lorem pson who cares. lol")
                .build();

        AddNewTodoResponse response = userService.addNewTodo(request);

        assertThat(response).isNotNull();
    }

    @Test
    void viewAllTodo() {
    }

    @Test
    void findTodoByTopic() {
    }

    @Test
    void updateTodo() {
    }
}