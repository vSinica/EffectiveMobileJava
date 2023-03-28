package ru.vados.effectivemobile.Service;

import ru.vados.effectivemobile.Dto.CompanyDto;
import ru.vados.effectivemobile.Entity.CompanyEntity;
import ru.vados.effectivemobile.Entity.UserEntity;

import java.util.List;

public interface CompanyService {
    void addCompany(CompanyDto.Create company);

    List<CompanyEntity> getCompanyByUser(UserEntity user);
    void approvCompany(String companyName);
    void deleteCompany(Long id);
}
