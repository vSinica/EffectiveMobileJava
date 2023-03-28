package ru.vados.effectivemobile.Service;

import ru.vados.effectivemobile.Dto.DiscountDto;

public interface DiscountManageService {
    void addDiscount(DiscountDto.Create discount);
    void updateDiscount(DiscountDto.Update discount);
    void deleteDiscount(Long discount);
}
