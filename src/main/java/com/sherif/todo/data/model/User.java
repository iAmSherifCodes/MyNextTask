package com.sherif.todo.data.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity @Builder
@Table(name = "MntUser")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Todo> todoList;
}
