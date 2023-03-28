package ru.vados.effectivemobile.Dto;

import lombok.Value;

public class UserManageDto {

    @Value
    public static class AddBalance{
        Long userId;
        Long addBalance;
    }

    @Value
    public static class Lock{
        Long userId;
        boolean lockPosition;
    }
}
