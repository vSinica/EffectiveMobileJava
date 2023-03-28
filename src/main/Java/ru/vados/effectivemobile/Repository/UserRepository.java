package ru.vados.effectivemobile.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vados.effectivemobile.Entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByName(String name);

    Optional<UserEntity> findByRole(String role);
}
