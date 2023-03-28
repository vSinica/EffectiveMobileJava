package ru.vados.effectivemobile.Service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vados.effectivemobile.Dto.DiscountDto;
import ru.vados.effectivemobile.Entity.DiscountEntity;
import ru.vados.effectivemobile.Entity.ProductEntity;
import ru.vados.effectivemobile.Repository.DiscountRepository;
import ru.vados.effectivemobile.Repository.ProductRepository;
import ru.vados.effectivemobile.Service.DiscountManageService;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DiscountManageServiceImpl implements DiscountManageService {

    private final DiscountRepository discountRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void addDiscount(DiscountDto.Create discount){
        DiscountEntity entity = new DiscountEntity();
        entity.setDiscountSize(discount.getDiscountSize());
        entity.setExpiresAt(discount.getExpiresAt().atStartOfDay(ZoneId.systemDefault()).toInstant());
        entity.setProducts(new ArrayList<>());

        for (String name : discount.getProductNames()) {
            ProductEntity product = productRepository.findByName(name).orElseThrow();
            entity.addProduct(product);
            product.setDiscount(entity);
            productRepository.save(product);
        }
        discountRepository.save(entity);
    }

    @Override
    @Transactional
    public void updateDiscount(DiscountDto.Update discount){
        DiscountEntity entity = discountRepository.findById(discount.getDiscountId()).orElseThrow();
        entity.setDiscountSize(discount.getDiscountSize());
        entity.setExpiresAt(discount.getExpiresAt().atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<ProductEntity> oldProducts = entity.getProducts();

        List<ProductEntity> toDeleteProducts = oldProducts.stream()
                .filter(a ->  !discount.getProductNames().contains(a.getName())).collect(Collectors.toList()
                );

        List<String> toCreateProducts = discount.getProductNames().stream()
                .filter(a ->  !oldProducts.stream().map(ProductEntity::getName).collect(Collectors.toList())
                        .contains(a)).collect(Collectors.toList()
                );

        toDeleteProducts.forEach(a -> {
            a.setDiscount(null);
            productRepository.save(a);
        });

        toCreateProducts
                .forEach(a -> {
                    ProductEntity product = productRepository.findByName(a).orElseThrow();
                    product.setDiscount(entity);
                    productRepository.save(product);
                    oldProducts.add(product);
                });

        entity.setProducts(oldProducts);
        discountRepository.save(entity);
    }

    @Override
    @Transactional
    public void deleteDiscount(Long discount){
        DiscountEntity entity = discountRepository.findById(discount).orElseThrow();

        List<ProductEntity> products = entity.getProducts();

        products.forEach(a -> {
            a.setDiscount(null);
            productRepository.save(a);
        });

        discountRepository.delete(entity);
    }

}
