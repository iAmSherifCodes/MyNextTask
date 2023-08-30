package com.sherif.todo.data.repository;

import com.sherif.todo.data.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
