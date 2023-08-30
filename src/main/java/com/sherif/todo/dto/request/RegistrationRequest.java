package com.sherif.todo.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class RegistrationRequest {
    private String name;
    private String email;
    private String password;
}
