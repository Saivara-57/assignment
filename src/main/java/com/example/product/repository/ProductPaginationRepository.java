package com.example.product.repository;

import com.example.product.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;

public interface ProductPaginationRepository extends PagingAndSortingRepository<ProductEntity, String> {

    Page<ProductEntity> findAllByCategory(String category,Pageable pageable);

}
