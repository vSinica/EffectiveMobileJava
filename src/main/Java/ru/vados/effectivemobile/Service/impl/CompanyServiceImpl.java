package ru.vados.effectivemobile.Service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vados.effectivemobile.Dto.CompanyDto;
import ru.vados.effectivemobile.Entity.CompanyEntity;
import ru.vados.effectivemobile.Entity.UserEntity;
import ru.vados.effectivemobile.Repository.CompanyRepository;
import ru.vados.effectivemobile.Service.CompanyService;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    @Transactional
    public void addCompany(CompanyDto.Create company){
        CompanyEntity entity = new CompanyEntity();
        entity.setName(company.getName());
        entity.setLogo(company.getLogo());
        entity.setDescription(company.getDescription());
        companyRepository.save(entity);
    }

    @Override
    @Transactional
    public List<CompanyEntity> getCompanyByUser(UserEntity user){
        return companyRepository.findByUser(user);
    }

    @Override
    @Transactional
    public void approvCompany(String companyName){
        CompanyEntity entity = companyRepository.findByName(companyName).orElseThrow();
        entity.setAprooved(true);
        companyRepository.save(entity);
    }

    @Override
    @Transactional
    public void deleteCompany(Long id){
        CompanyEntity entity = companyRepository.findById(id).orElseThrow();
        entity.setLocked(true);
        companyRepository.save(entity);
    }

}
