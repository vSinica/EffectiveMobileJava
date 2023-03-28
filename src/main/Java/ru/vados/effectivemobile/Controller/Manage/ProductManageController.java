package ru.vados.effectivemobile.Controller.Manage;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vados.effectivemobile.Dto.ProductDto;
import ru.vados.effectivemobile.Service.ProductService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/manage/v1/")
public class ProductManageController {

    private final ProductService productService;

    @PutMapping("/addProductManage")
    public void addProduct(@RequestBody ProductDto.Create product){
        productService.addProduct(product);
    }

    @PostMapping("updateProductManage")
    public void updateProduct(@RequestBody ProductDto.Update product){
        productService.updateProduct(product);
    }

    @DeleteMapping("deleteProductManage")
    public void deleteProduct(@RequestBody String productName){
        productService.deleteProduct(productName);
    }

    @PostMapping("aprooveProduct")
    public void aprooveProduct(@RequestBody ProductDto.Aproove product){
        productService.aprooveProduct(product);
    }

}
