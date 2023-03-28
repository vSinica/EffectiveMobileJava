package ru.vados.effectivemobile.Service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vados.effectivemobile.Dto.NotificationDto;
import ru.vados.effectivemobile.Dto.UserItem;
import ru.vados.effectivemobile.Dto.UserManageDto;
import ru.vados.effectivemobile.Entity.NotificationEntity;
import ru.vados.effectivemobile.Entity.UserEntity;
import ru.vados.effectivemobile.Repository.NotificationRepository;
import ru.vados.effectivemobile.Repository.UserRepository;
import ru.vados.effectivemobile.Service.UserManageService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserManageServiceImpl implements UserManageService {

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    @Override
    @Transactional
    public void addUserBalance(UserManageDto.AddBalance balance){
        UserEntity user = userRepository.findById(balance.getUserId()).orElseThrow();
        user.setBalance(user.getBalance().add(BigDecimal.valueOf(balance.getAddBalance())));

        userRepository.save(user);
    }

    @Override
    @Transactional
    public ResponseEntity<Iterable<UserItem>> getUsers(){
        List<UserItem> userItems = userRepository.findAll().stream()
                .map(a -> new UserItem(
                        a.getId(),
                        a.getName(),
                        a.getEmail(),
                        a.getPassword(),
                        a.getBalance(),
                        a.getRole())
                ).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(userItems);
    }

    @Override
    @Transactional
    public ResponseEntity<UserItem> getUser(Long id){
        UserEntity user = userRepository.findById(id).orElseThrow();
        return ResponseEntity.status(HttpStatus.OK).body(
                new UserItem(user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getBalance(),
                user.getRole())
        );
    }

    @Override
    @Transactional
    public void lockedUser(UserManageDto.Lock lock) {
        UserEntity user = userRepository.findById(lock.getUserId()).orElseThrow();
        user.setLocked(lock.isLockPosition());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void makeNotification(NotificationDto.Create ntf) {
        NotificationEntity entity = new NotificationEntity();
        entity.setUser(userRepository.findById(ntf.getUserId()).orElseThrow());
        entity.setDescription(ntf.getDescription());
        entity.setHeader(ntf.getHeader());

        notificationRepository.save(entity);
    }


}
