package ru.vados.effectivemobile.Service;

import org.springframework.http.ResponseEntity;
import ru.vados.effectivemobile.Dto.ProductDto;
import ru.vados.effectivemobile.Dto.ProductItem;

public interface ProductService {

    void addProduct(ProductDto.Create productData);
    void updateProduct(ProductDto.Update productData);

    void deleteProduct(String productName);
    ResponseEntity<Iterable<ProductItem>> getProducts();
    void aprooveProduct(ProductDto.Aproove product);
}
