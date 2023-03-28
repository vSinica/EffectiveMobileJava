package ru.vados.effectivemobile.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.vados.effectivemobile.Dto.ProductDto;
import ru.vados.effectivemobile.Dto.ProductItem;
import ru.vados.effectivemobile.Entity.UserEntity;
import ru.vados.effectivemobile.Repository.UserRepository;
import ru.vados.effectivemobile.Security.CustomUserDetails;
import ru.vados.effectivemobile.Service.CompanyService;
import ru.vados.effectivemobile.Service.ProductService;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/")
public class ProductController {

    private final ProductService productService;
    private final CompanyService companyService;
    private final UserRepository userRepository;

    @PutMapping("/addProduct")
    public void addProduct(@RequestBody ProductDto.Create product){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userRepository.findById(customUserDetails.getUser().getId()).orElseThrow();

        if(companyService.getCompanyByUser(user).stream().noneMatch(a -> a.getName().equals(product.getCompanyName()))){
            return;
        }
        productService.addProduct(product);
    }

    @PostMapping("updateProduct")
    public void updateProduct(@RequestBody ProductDto.Update product){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userRepository.findById(customUserDetails.getUser().getId()).orElseThrow();

        if(companyService.getCompanyByUser(user).stream().noneMatch(a -> a.getName().equals(product.getCompanyName()))){
            return;
        }
        productService.updateProduct(product);
    }

    @DeleteMapping("deleteProduct")
    public void deleteProduct(@RequestBody String productName){
        productService.deleteProduct(productName);
    }

    @GetMapping("/getProducts")
    public ResponseEntity<Iterable<ProductItem>> getProduct(){
        return productService.getProducts();
    }

}
