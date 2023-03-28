package ru.vados.effectivemobile.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vados.effectivemobile.Entity.CompanyEntity;
import ru.vados.effectivemobile.Entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    Optional<CompanyEntity> findByName(String name);
    List<CompanyEntity> findByUser(UserEntity user);
}
