package ru.vados.effectivemobile.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "companies")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private List<ProductEntity> products;

    private String name;

    private String description;

    private String logo;

    private boolean aprooved;

    private boolean locked;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public void addProduct(ProductEntity product){
        products.add(product);
    }

    public void removeProduct(ProductEntity product){
        products.remove(product);
    }
}
