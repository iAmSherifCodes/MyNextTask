package com.sherif.todo.service;

import com.sherif.todo.dto.request.AddNewTodoRequest;
import com.sherif.todo.dto.request.RegistrationRequest;
import com.sherif.todo.dto.request.UpdateTodoRequest;
import com.sherif.todo.dto.response.AddNewTodoResponse;
import com.sherif.todo.dto.response.TodoResponse;
import com.sherif.todo.dto.response.UpdateTodoResponse;
import com.sherif.todo.dto.response.ViewAllTodoResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

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
                .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
                .build();

        AddNewTodoResponse response = userService.addNewTodo(request);

        assertThat(response).isNotNull();
    }

    @Test
    void viewAllTodo() {
        AddNewTodoRequest request = AddNewTodoRequest.builder()
                .heading("My First Task")
                .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
                .build();

        AddNewTodoRequest request2 = AddNewTodoRequest.builder()
                .heading("My Second Task")
                .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
                .build();

        AddNewTodoResponse response = userService.addNewTodo(request);

        assertThat(response).isNotNull();

        AddNewTodoResponse response2 = userService.addNewTodo(request2);

        assertThat(response2).isNotNull();

        ViewAllTodoResponse response1 = userService.viewAllTodo();
        assertThat(response1).isNotNull();

    }

    @Test
    void findTodoByTopic() {
        AddNewTodoRequest request = AddNewTodoRequest
                .builder()
                .heading("My First Task")
                .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
                .build();
        AddNewTodoResponse addNewTodoResponse = userService.addNewTodo(request);
        assertThat(addNewTodoResponse).isNotNull();

        String heading = "My First Task";
        TodoResponse response = userService.findTodoByHeading(heading);
        assertThat(response.getDescription()).isNotNull();


    }

    @Test
    void updateTodo() {
        AddNewTodoRequest request = AddNewTodoRequest
                .builder()
                .heading("My First Task")
                .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
                .build();
        AddNewTodoResponse addNewTodoResponse = userService.addNewTodo(request);
        assertThat(addNewTodoResponse).isNotNull();

        String heading = "My First Task";
        TodoResponse foundTodo = userService.findTodoByHeading(heading);
        assertThat(foundTodo).isNotNull();

        String updateHeading = "My Changed Task";

        UpdateTodoRequest updateTodoRequest = new UpdateTodoRequest();
        updateTodoRequest.setHeading(updateHeading);

        UpdateTodoResponse updateTodoResponse = userService.updateTodo(updateTodoRequest);
        assertThat(updateTodoResponse).isNotNull();

        TodoResponse updatedTodo = userService.findTodoByHeading(updateHeading);
        assertThat(updatedTodo.getHeading()).isEqualTo(updateHeading);

    }
}