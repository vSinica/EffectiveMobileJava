package ru.vados.effectivemobile.Dto;

import lombok.Value;

import java.time.Instant;

@Value
public class NotificationItem {
    String header;
    String description;
    Instant createdAt;

}
