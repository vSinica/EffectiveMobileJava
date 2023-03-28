package ru.vados.effectivemobile.Service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vados.effectivemobile.Dto.ProductDto;
import ru.vados.effectivemobile.Dto.ProductItem;
import ru.vados.effectivemobile.Entity.CompanyEntity;
import ru.vados.effectivemobile.Entity.DiscountEntity;
import ru.vados.effectivemobile.Entity.ProductEntity;
import ru.vados.effectivemobile.Repository.CompanyRepository;
import ru.vados.effectivemobile.Repository.ProductRepository;
import ru.vados.effectivemobile.Service.ProductService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CompanyRepository companyRepository;

    @Override
    @Transactional
    public void addProduct(ProductDto.Create productData){
        ProductEntity entity = new ProductEntity();
        entity.setName(productData.getName());
        entity.setDescription(productData.getDescription());
        entity.setPrice(productData.getPrice());
        entity.setInStock(productData.getInStock());

        CompanyEntity company = companyRepository.findByName(productData.getCompanyName()).orElseThrow();
        company.setProducts(new ArrayList<>());
        company.addProduct(entity);
        entity.setCompany(company);

        productRepository.save(entity);
        
    }

    @Override
    @Transactional
    public void updateProduct(ProductDto.Update productData){
        ProductEntity entity = productRepository.findByName(productData.getOldName()).orElseThrow();

        if(!entity.getCompany().getName().equals(productData.getCompanyName())){
            CompanyEntity oldCompany = entity.getCompany();
            oldCompany.removeProduct(entity);
            companyRepository.save(oldCompany);

            entity.setName(productData.getName());
            entity.setDescription(productData.getDescription());
            entity.setPrice(productData.getPrice());
            entity.setInStock(productData.getInStock());

            CompanyEntity newCompany = companyRepository.findByName(productData.getCompanyName()).orElseThrow();
            newCompany.addProduct(entity);
            companyRepository.save(newCompany);

            productRepository.save(entity);
        } else {
            entity.setName(productData.getName());
            entity.setDescription(productData.getDescription());
            entity.setPrice(productData.getPrice());
            entity.setInStock(productData.getInStock());

            productRepository.save(entity);
        }
    }

    @Override
    @Transactional
    public void deleteProduct(String productName) {
        ProductEntity entity = productRepository.findByName(productName).orElseThrow();

        CompanyEntity company = entity.getCompany();
        company.removeProduct(entity);
        companyRepository.save(company);

        productRepository.delete(entity);
    }

    @Override
    @Transactional
    public ResponseEntity<Iterable<ProductItem>> getProducts() {
       return ResponseEntity.ok().body(productRepository.findByAprooveIsTrue().stream()
               .map(a -> new ProductItem(
                       a.getName(),
                       a.getDescription(),
                       a.getCompany().getName(),
                       a.getPrice(),
                       a.getInStock(),
                       Optional.ofNullable(a.getDiscount()).map(DiscountEntity::getDiscountSize).orElse(0L),
                       a.getRating(),
                       a.isAproove()
               )).collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public void aprooveProduct(ProductDto.Aproove product) {
        ProductEntity entity = productRepository.findByName(product.getName()).orElseThrow();
        entity.setAproove(product.isState());
        productRepository.save(entity);
    }

}
