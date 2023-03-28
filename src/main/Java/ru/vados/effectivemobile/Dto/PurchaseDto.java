package ru.vados.effectivemobile.Dto;

import lombok.Data;

@Data
public class PurchaseDto {

    @Data
    public static class Create{
        Long userId;
        Long productId;
    }

    @Data
    public static class Refund{
        Long userId;
        Long purchaseId;
    }
}
