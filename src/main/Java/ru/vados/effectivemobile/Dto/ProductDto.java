package ru.vados.effectivemobile.Dto;

import lombok.Data;


@Data
public class ProductDto {

    @Data
    public static class Create {
        String name;
        String description;
        Long price;
        Long inStock;
        String companyName;
    }

    @Data
    public static class Update {
        String oldName;
        String name;
        String description;
        Long price;
        Long inStock;
        String companyName;
    }

    @Data
    public static class Delete {
        String name;
    }

    @Data
    public static class Aproove {
        String name;
        boolean state;
    }

}
