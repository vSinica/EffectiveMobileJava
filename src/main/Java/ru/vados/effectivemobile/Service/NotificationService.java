package ru.vados.effectivemobile.Service;

import org.springframework.http.ResponseEntity;
import ru.vados.effectivemobile.Dto.NotificationItem;

public interface NotificationService {
    ResponseEntity<Iterable<NotificationItem>> getNotifications(Long userId);
}
