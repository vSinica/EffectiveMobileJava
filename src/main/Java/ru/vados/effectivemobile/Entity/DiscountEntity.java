package ru.vados.effectivemobile.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "discounts")
public class DiscountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "discount")
    private List<ProductEntity> products = new ArrayList<>();

    private Long discountSize;

    private Instant expiresAt;

    public void addProduct(ProductEntity product){
        products.add(product);
    }

}
