package ru.vados.effectivemobile.Dto;

import lombok.Value;

@Value
public class PurchaseItem {
    String productName;
    Long price;
    String description;
    String companyName;
    Long id;
}
