package com.sherif.todo.data.model;

import com.sherif.todo.data.enums.Role;
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
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Todo> todoList;
}
