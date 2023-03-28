package ru.vados.effectivemobile.Dto;

import lombok.Value;

@Value
public class RatingDto {

    @Value
    public static class Create{
        Long rating;
        Long productId;
    }

}
