package ru.vados.effectivemobile.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vados.effectivemobile.Entity.ReviewEntity;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
}
