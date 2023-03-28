package ru.vados.effectivemobile.Service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vados.effectivemobile.Dto.PurchaseDto;
import ru.vados.effectivemobile.Dto.PurchaseItem;
import ru.vados.effectivemobile.Dto.RatingDto;
import ru.vados.effectivemobile.Dto.ReviewDto;
import ru.vados.effectivemobile.Entity.*;
import ru.vados.effectivemobile.Repository.*;
import ru.vados.effectivemobile.Service.PurchaseService;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final CompanyRepository companyRepository;

    @Override
    @Transactional
    public ResponseEntity<Object> addPurchase(PurchaseDto.Create purchase) {
        ProductEntity product = productRepository.findByIdAndAprooveIsTrue(purchase.getProductId()).orElseThrow();
        if(product.getCompany().isLocked() && product.getCompany().isAprooved()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }

        UserEntity user = userRepository.findById(purchase.getUserId()).orElseThrow();
        PurchaseEntity entity = new PurchaseEntity();

        if( user.getBalance().longValue() <  product.getPrice()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } else {
            entity.setProduct(product);
            entity.setUser(user);
            entity.setPrice(BigDecimal.valueOf(product.getPrice()));
            purchaseRepository.save(entity);

            UserEntity admin = userRepository.findByRole("ADMIN").orElseThrow();
            BigDecimal adminBalance = admin.getBalance();
            adminBalance = adminBalance.add(BigDecimal.valueOf(product.getPrice()*0.05d));
            admin.setBalance(adminBalance);
            userRepository.save(admin);

            UserEntity owner =   userRepository.findById(
                    Optional.ofNullable(product.getCompany()).orElseThrow()
                            .getUser().getId()).orElseThrow();
            BigDecimal ownerBalance = owner.getBalance();
            ownerBalance = ownerBalance.add(BigDecimal.valueOf(product.getPrice()*0.95d));
            owner.setBalance(ownerBalance);
            userRepository.save(owner);

            BigDecimal balance = user.getBalance();
            balance = balance.subtract(BigDecimal.valueOf(product.getPrice()));
            user.setBalance(balance);
            userRepository.save(user);

            return ResponseEntity.status(HttpStatus.OK).build();
        }

    }

    @Override
    @Transactional
    public List<PurchaseItem> getPurchases(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow();
        List<PurchaseEntity> purchases = purchaseRepository.findByUser(user);

        return purchases.stream()
                .map(a -> new PurchaseItem(
                        a.getProduct().getName(),
                        a.getProduct().getPrice(),
                        a.getProduct().getDescription(),
                        a.getProduct().getCompany().getName(),
                        a.getId()
                ))
                .collect(Collectors.toList());

    }

    @Override
    @Transactional
    public ResponseEntity<Object> doRefund(PurchaseDto.Refund refundInfo) {
        ResponseEntity<Object> response;

        response = purchaseRepository.findByIdAndCreatedAtAfter(
                        refundInfo.getPurchaseId(),
                        Instant.now().minus(Period.ofDays(1)))
                .map(a -> {
                            UserEntity user = userRepository.findById(refundInfo.getUserId()).orElseThrow();
                            BigDecimal balance = user.getBalance();
                            balance = balance.add(a.getPrice());
                            user.setBalance(balance);
                            userRepository.save(user);

                            UserEntity admin = userRepository.findByRole("ADMIN").orElseThrow();
                            BigDecimal adminBalance = admin.getBalance();
                            adminBalance = adminBalance.subtract(BigDecimal.valueOf(a.getPrice().doubleValue() * 0.05d));
                            admin.setBalance(adminBalance);
                            userRepository.save(admin);

                            UserEntity owner = userRepository.findById(
                                    Optional.ofNullable(a.getProduct().getCompany()).orElseThrow()
                                            .getUser().getId()).orElseThrow();
                            BigDecimal ownerBalance = owner.getBalance();
                            ownerBalance = ownerBalance.subtract(BigDecimal.valueOf(a.getPrice().doubleValue() * 0.95d));
                            owner.setBalance(ownerBalance);
                            userRepository.save(owner);

                            purchaseRepository.delete(a);
                            return ResponseEntity.status(HttpStatus.OK).build();
                        }
                ).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());

        return response;
    }

    @Override
    @Transactional
    public void addReview(ReviewDto.Create review){
        UserEntity user = userRepository.findById(1L).orElseThrow();
        ProductEntity product = productRepository.findById(review.getProductId()).orElseThrow();

        if(!purchaseRepository.findByUserAndProduct(user, product).isEmpty()){
            ReviewEntity entity = new ReviewEntity();
            entity.setBody(review.getBody());
            entity.setHeader(review.getHeader());
            entity.setProduct(productRepository.findById(review.getProductId()).orElseThrow());

            reviewRepository.save(entity);
        }
    }

    @Override
    @Transactional
    public void addRating(RatingDto.Create rating){
        ProductEntity product = productRepository.findById(rating.getProductId()).orElseThrow();

        Long oldRating = product.getRating();
        oldRating = (oldRating + rating.getRating())/2;
        product.setRating(oldRating);

        productRepository.save(product);
    }

}
