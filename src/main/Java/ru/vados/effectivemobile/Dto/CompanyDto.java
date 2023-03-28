package ru.vados.effectivemobile.Dto;

import lombok.Data;
import lombok.Value;

@Data
public class CompanyDto {

    @Value
    public static class Create {
        String name;
        String description;
        String logo;
    }

}
