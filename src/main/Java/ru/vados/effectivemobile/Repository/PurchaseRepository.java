package ru.vados.effectivemobile.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vados.effectivemobile.Entity.ProductEntity;
import ru.vados.effectivemobile.Entity.PurchaseEntity;
import ru.vados.effectivemobile.Entity.UserEntity;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Long> {
    List<PurchaseEntity> findByUser(UserEntity user);

    Optional<PurchaseEntity> findByIdAndCreatedAtAfter(Long purchaseId, Instant date);

    List<PurchaseEntity> findByUserAndProduct(UserEntity user, ProductEntity product);
}
