package ru.vados.effectivemobile.Controller.Manage;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vados.effectivemobile.Service.CompanyService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/manage/v1/")
public class CompanyManageController {

    private final CompanyService companyService;

    @PutMapping("/approveCompany")
    public void approveCompany(@RequestBody String company){
        companyService.approvCompany(company);
    }

    @DeleteMapping("/deleteCompany/{id}")
    public void deleteCompany(@PathVariable Long id){
        companyService.deleteCompany(id);
    }

}
