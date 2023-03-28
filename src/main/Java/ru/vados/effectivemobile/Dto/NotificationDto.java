package ru.vados.effectivemobile.Dto;

import lombok.Data;
import lombok.Value;

import java.time.Instant;

@Data
public class NotificationDto {

    @Value
    public static class Create {
        String description;
        String header;
        Instant createdAt;
        Long userId;
    }

}
