package ru.vados.effectivemobile.Controller.Manage;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vados.effectivemobile.Dto.*;
import ru.vados.effectivemobile.Service.PurchaseService;
import ru.vados.effectivemobile.Service.UserManageService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/manage/v1/")
public class UserManageController {

    private final UserManageService userManageService;
    private final PurchaseService purchaseService;

    @PostMapping("addUserBalance")
    public void addBalance(@RequestBody UserManageDto.AddBalance balance){
        userManageService.addUserBalance(balance);
    }

    @GetMapping("getUsers")
    public ResponseEntity<Iterable<UserItem>> getUsers(){
        return userManageService.getUsers();
    }

    @GetMapping("getUser/{id}")
    public ResponseEntity<UserItem> getUser(@PathVariable Long id){
        return userManageService.getUser(id);
    }

    @GetMapping("/getPurchase/{id}")
    public List<PurchaseItem> getPurchase(@PathVariable Long userId){
        return purchaseService.getPurchases(userId);
    }

    @PostMapping("/locked")
    public void lockedUser(@RequestBody UserManageDto.Lock lock){
        userManageService.lockedUser(lock);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable String id){
        userManageService.deleteUser(Long.parseLong(id));
    }

    @DeleteMapping("/makeNotification")
    public void makeNotification(@RequestBody NotificationDto.Create ntf) {
        userManageService.makeNotification(ntf);
    }

}
