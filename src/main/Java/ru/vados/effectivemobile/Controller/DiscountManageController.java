package ru.vados.effectivemobile.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vados.effectivemobile.Dto.DiscountDto;
import ru.vados.effectivemobile.Service.DiscountManageService;

@RestController
@AllArgsConstructor
@RequestMapping("api/manage/v1/")
public class DiscountManageController {

    private final DiscountManageService discountManageService;

    @PutMapping("addDiscount")
    void addDiscount(@RequestBody DiscountDto.Create discount){
        discountManageService.addDiscount(discount);
    }

    @PostMapping("updateDiscount")
    void updateDiscount(@RequestBody DiscountDto.Update discount){
        discountManageService.updateDiscount(discount);
    }

    @DeleteMapping("deleteDiscount")
    void updateDiscount(@RequestBody Long discount){
        discountManageService.deleteDiscount(discount);
    }
}
