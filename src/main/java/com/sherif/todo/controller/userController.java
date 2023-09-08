package com.sherif.todo.controller;

import com.sherif.todo.dto.request.AddNewTodoRequest;
import com.sherif.todo.dto.request.RegistrationRequest;
import com.sherif.todo.dto.request.UpdateTodoRequest;
import com.sherif.todo.dto.response.*;
import com.sherif.todo.service.TodoAppUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @AllArgsConstructor
@RequestMapping("api/todo")
public class userController {

    private final TodoAppUserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> register(@RequestBody RegistrationRequest registrationRequest){
        return ResponseEntity.ok(userService.register(registrationRequest));
    }

    @PostMapping("/add-todo")
    public ResponseEntity<AddNewTodoResponse> addTodo(@RequestBody AddNewTodoRequest addNewTodoRequest){
        return new ResponseEntity<>(userService.addNewTodo(addNewTodoRequest), HttpStatus.OK);
    }

    @GetMapping("/view-all-todo")
    public ResponseEntity<ViewAllTodoResponse> viewAllTodo(){
        return new ResponseEntity<>(userService.viewAllTodo(), HttpStatus.OK);
    }

    @GetMapping("/find/{heading}")
    public ResponseEntity<TodoResponse> getTodoByHeading(@PathVariable String heading){
        return new ResponseEntity<>(userService.findTodoByHeading(heading), HttpStatus.OK);
    }

    @PatchMapping("/update-todo/{heading}")
    public ResponseEntity<UpdateTodoResponse> updateTodo(@RequestBody UpdateTodoRequest updateTodoRequest, @PathVariable String heading){
        return new ResponseEntity<>(userService.updateTodo(updateTodoRequest, heading), HttpStatus.OK);
    }

    @DeleteMapping("/delete-todo/{heading}")
    public ResponseEntity<DeleteTodoResponse> deleteTodo(@PathVariable String heading){
        return new ResponseEntity<>(userService.deleteTodo(heading), HttpStatus.OK);
    }




}
