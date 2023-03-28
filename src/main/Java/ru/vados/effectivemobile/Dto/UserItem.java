package ru.vados.effectivemobile.Dto;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class UserItem {
     Long id;
     String name;
     String email;
     String password;
     BigDecimal balance;
     String role;
}
