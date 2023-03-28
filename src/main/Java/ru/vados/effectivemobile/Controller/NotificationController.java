package ru.vados.effectivemobile.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vados.effectivemobile.Dto.NotificationItem;
import ru.vados.effectivemobile.Security.CustomUserDetails;
import ru.vados.effectivemobile.Service.NotificationService;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/getNotification")
    public ResponseEntity<Iterable<NotificationItem>> getNotification(){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return notificationService.getNotifications(customUserDetails.getUser().getId());
    }

}
