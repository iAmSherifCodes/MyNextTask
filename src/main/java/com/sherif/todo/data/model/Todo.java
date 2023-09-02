package com.sherif.todo.data.model;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name= "Todo")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Todo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String heading;
    @Column(length = 2000)
    private String description;

//    @JsonSerialize(using = JsonSerializer.class)
//    @JsonDeserialize(using = JsonDeserializer.class)
    private String dateTime ;

    @PrePersist
    public void setDateTime(){
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        dateTime = currentTime.format(formatter);
    }
}
