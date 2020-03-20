package com.example.product.service;

import com.example.product.constants.ProductConstant;
import com.example.product.entity.ProductEntity;
import com.example.product.exception.ProductCatalogException;
import com.example.product.exception.ProductNotFoundException;
import com.example.product.model.ProductRequest;
import com.example.product.model.ProductResponse;
import com.example.product.repository.ProductPaginationRepository;
import com.example.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    @Autowired
    ProductPaginationRepository productPaginationRepository;

    public List<ProductResponse> getAllProducts() {
        List<ProductEntity> productList = repository.findAll();

        if (productList.size() > 0) {
            return convertToProducts(productList);
        } else {
            throw new ProductNotFoundException("No product record exist (zero product)");
        }
    }

    public List<ProductResponse> searchByCategory(String category, Integer page, Integer pageSize) {
        Page<ProductEntity> products = productPaginationRepository.findAllByCategory(category,
                PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, ProductConstant.CREATED_AT)));
        if (products != null && !products.isEmpty()) {
            return convertToProducts(products.getContent());
        } else {
            throw new ProductNotFoundException("No product record exist for given category");
        }
    }


    /**
     * Svae/update the product into database
     */
    public ProductResponse createOrUpdateProduct(ProductRequest product) {
        ProductEntity productEntity = repository.save(convertToEntity(product));
        if (productEntity == null) {
            throw new ProductCatalogException("Error in  storing the product in to Database");
        }
        return convertToProduct(productEntity);

    }

    private List<ProductResponse> convertToProducts(List<ProductEntity> productList) {
        return productList.stream().map(a -> convertToProduct(a)).collect(Collectors.toList());
    }

    /**
     * Instead of exposing entity to end users, rather using the custom response so conversion is required
     */
    private ProductResponse convertToProduct(ProductEntity productEntity) {
        if (productEntity == null) {
            return null;
        }
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(productEntity.getId());
        productResponse.setBrand(productEntity.getBrand());
        productResponse.setName(productEntity.getName());
        productResponse.setCategory(productEntity.getCategory());
        productResponse.setDescription(productEntity.getDescription());
        productResponse.setTags(productEntity.getTags());
        productResponse.setCreatedAt(productEntity.getCreatedAt().format(ProductConstant.formatter));
        return productResponse;

    }


    /**
     * This function converts the custom object to ProductEntity object
     */
    private ProductEntity convertToEntity(ProductRequest product) {
        if (product == null) {
            return null;
        }
        ProductEntity productEntity = new ProductEntity();
        productEntity.setBrand(product.getBrand());
        productEntity.setName(product.getName());
        productEntity.setCategory(product.getCategory());
        productEntity.setDescription(product.getDescription());
        productEntity.setTags(product.getTags());
        productEntity.setCreatedAt(LocalDateTime.now());
        return productEntity;
    }

}