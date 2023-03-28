package ru.vados.effectivemobile.Dto;

import lombok.Data;
import lombok.Value;

@Data
public class ReviewDto {

    @Value
    public static class Create{
        String header;
        String body;
        Long productId;
    }

}
