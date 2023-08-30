package com.sherif.todo.data.model;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name= "Todo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String heading;
    private String description;

    @JsonSerialize(using = JsonSerializer.class)
    @JsonDeserialize(using = JsonDeserializer.class)
    private LocalDateTime dateTime = LocalDateTime.now();
}
