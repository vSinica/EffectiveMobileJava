package ru.vados.effectivemobile.Service;

import org.springframework.http.ResponseEntity;
import ru.vados.effectivemobile.Dto.NotificationDto;
import ru.vados.effectivemobile.Dto.UserItem;
import ru.vados.effectivemobile.Dto.UserManageDto;

public interface UserManageService {

    void addUserBalance(UserManageDto.AddBalance balance);
    ResponseEntity<Iterable<UserItem>> getUsers();
    ResponseEntity<UserItem> getUser(Long id);
    void  lockedUser(UserManageDto.Lock id);
    void deleteUser(Long id);
    void makeNotification(NotificationDto.Create ntf);

}
