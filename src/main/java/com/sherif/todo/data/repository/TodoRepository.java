package com.sherif.todo.data.repository;

import com.sherif.todo.data.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Optional<Todo> findByHeading (String heading);

}
