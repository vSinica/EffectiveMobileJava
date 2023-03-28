package ru.vados.effectivemobile.Service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vados.effectivemobile.Dto.NotificationItem;
import ru.vados.effectivemobile.Entity.NotificationEntity;
import ru.vados.effectivemobile.Repository.NotificationRepository;
import ru.vados.effectivemobile.Repository.UserRepository;
import ru.vados.effectivemobile.Service.NotificationService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ResponseEntity<Iterable<NotificationItem>> getNotifications(Long userId){
        List<NotificationEntity> entity = notificationRepository.findByUser(userRepository.findById(userId).orElseThrow());

        return ResponseEntity.status(HttpStatus.OK).body(entity.stream()
                .map(a -> new NotificationItem(
                        a.getHeader(),
                        a.getDescription(),
                        a.getCreatedAt()
                )).collect(Collectors.toList())
        );
    }
}
