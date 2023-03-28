package ru.vados.effectivemobile.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private BigDecimal balance;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<PurchaseEntity> purchases;

    private String role;

    private boolean locked;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<NotificationEntity> notification;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<CompanyEntity> companies;
}
