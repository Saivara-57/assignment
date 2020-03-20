package com.example.product.service;

import com.example.product.constants.ProductConstant;
import com.example.product.entity.ProductEntity;
import com.example.product.exception.RecordNotFoundException;
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
            return new ArrayList<ProductResponse>();
        }
    }

    public ProductResponse getProductById(String id) throws RecordNotFoundException {
        Optional<ProductEntity> product = repository.findById(id);

        if (product.isPresent()) {
            return convertToProduct(product.get());
        } else {
            throw new RecordNotFoundException("No product record exist for given id");
        }
    }

    public List<ProductResponse> searchByCategory(String category, Integer page, Integer pageSize) {
        Page<ProductEntity> products = productPaginationRepository.findAllByCategory(category,
                PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, ProductConstant.CREATED_AT)));
        if (products != null && !products.isEmpty()) {
            return convertToProducts(products.getContent());
        }
        return new ArrayList<>();
    }

    public ProductResponse createOrUpdateProduct(ProductRequest product) {
        return convertToProduct(repository.save(convertToEntity(product)));

    }

    private List<ProductResponse> convertToProducts(List<ProductEntity> productList) {
        return productList.stream().map(a -> convertToProduct(a)).collect(Collectors.toList());
    }

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