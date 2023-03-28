package ru.vados.effectivemobile.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vados.effectivemobile.Dto.CompanyDto;
import ru.vados.effectivemobile.Service.CompanyService;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/")
public class CompanyController {

    private final CompanyService companyService;

    @PutMapping("/addCompany")
    public void addCompany(@RequestBody CompanyDto.Create company){
        companyService.addCompany(company);
    }

}
