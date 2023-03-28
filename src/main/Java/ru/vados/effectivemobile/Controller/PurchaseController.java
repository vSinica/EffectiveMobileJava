package ru.vados.effectivemobile.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.vados.effectivemobile.Dto.*;
import ru.vados.effectivemobile.Security.CustomUserDetails;
import ru.vados.effectivemobile.Service.PurchaseService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PutMapping("/addPurchase")
    public ResponseEntity<Object> addPurchase(@RequestBody PurchaseDto.Create purchase){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        purchase.setUserId(customUserDetails.getUser().getId());
        return purchaseService.addPurchase(purchase);
    }

    @GetMapping("/getPurchases")
    public List<PurchaseItem> getPurchases(){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return purchaseService.getPurchases(customUserDetails.getUser().getId());
    }

    @DeleteMapping ("/doRefund")
    public ResponseEntity<Object> doRefund(@RequestBody PurchaseDto.Refund refundInfo) {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        refundInfo.setUserId(customUserDetails.getUser().getId());
        return purchaseService.doRefund(refundInfo);
    }

    @PostMapping("/addReview")
    public void addReview(@RequestBody ReviewDto.Create review){
        purchaseService.addReview(review);
    }

    @PostMapping("/addRating")
    public void addRating(@RequestBody RatingDto.Create rating){
        purchaseService.addRating(rating);
    }
}
