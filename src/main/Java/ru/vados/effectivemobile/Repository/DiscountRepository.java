package ru.vados.effectivemobile.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vados.effectivemobile.Entity.DiscountEntity;

public interface DiscountRepository extends JpaRepository<DiscountEntity, Long> {
}
