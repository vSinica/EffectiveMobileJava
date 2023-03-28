package ru.vados.effectivemobile.Service;

import org.springframework.http.ResponseEntity;
import ru.vados.effectivemobile.Dto.PurchaseDto;
import ru.vados.effectivemobile.Dto.PurchaseItem;
import ru.vados.effectivemobile.Dto.RatingDto;
import ru.vados.effectivemobile.Dto.ReviewDto;

import java.util.List;

public interface PurchaseService {
    ResponseEntity<Object> addPurchase(PurchaseDto.Create purchase);
    List<PurchaseItem> getPurchases(Long userId);
    ResponseEntity<Object> doRefund(PurchaseDto.Refund refundInfo);
    void addReview(ReviewDto.Create review);

    void addRating(RatingDto.Create rating);

}
