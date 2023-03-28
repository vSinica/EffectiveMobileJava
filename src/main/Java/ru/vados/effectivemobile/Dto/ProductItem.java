package ru.vados.effectivemobile.Dto;

import lombok.Value;

@Value
public class ProductItem {
     String name;
     String description;
     String companyName;
     Long price;
     Long inStock;
     Long discountSize;
     Long rating;
     boolean aproove;

}
