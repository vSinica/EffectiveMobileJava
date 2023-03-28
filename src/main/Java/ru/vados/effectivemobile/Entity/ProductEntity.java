package ru.vados.effectivemobile.Entity;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.vados.effectivemobile.Dto.ProductMeta;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class ProductEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    private Long price;

    private Long inStock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discount_id")
    private DiscountEntity discount;

    private Long rating;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<ReviewEntity> reviews = new ArrayList<>();

    @Type(type = "json")
    @Column(updatable = false, insertable = false)
    private ProductMeta meta;

    boolean aproove;

}
