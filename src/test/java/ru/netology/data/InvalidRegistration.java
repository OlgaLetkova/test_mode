package ru.netology.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvalidRegistration {
    private int login;
    private String password;
    private String status;
}
