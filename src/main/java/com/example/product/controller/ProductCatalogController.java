package com.example.product.controller;

import com.example.product.api.ListApi;
import com.example.product.api.ProductApi;
import com.example.product.api.SearchApi;
import com.example.product.model.ProductRequest;
import com.example.product.model.ProductResponse;
import com.example.product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * This class exposes APIs for end-users to perform different operations on Product Catalog Service
 *
 * @author SV Prasad Veerla
 */
@Log
@RestController
@RequestMapping("/v1/products")
public class ProductCatalogController implements ProductApi, SearchApi, ListApi {

    @Autowired
    private ProductService productService;

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getAcceptHeader() {
        return Optional.empty();
    }

    @Override
    public ResponseEntity<ProductResponse> saveProduct(@Valid ProductRequest products) {
        return new ResponseEntity<>(productService.createOrUpdateProduct(products), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<ProductResponse>> getSearchByCategoty(String category, @NotNull @Valid Integer pageNumber, @NotNull @Valid Integer pageSize) {
        return new ResponseEntity<>(productService.searchByCategory(category, pageNumber, pageSize), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }
}
