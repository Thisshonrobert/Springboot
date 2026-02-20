package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.ProductDto;
import com.codewithmosh.store.entities.Product;
import com.codewithmosh.store.mappers.ProductMapper;

import com.codewithmosh.store.repositories.CategoryRepository;
import com.codewithmosh.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;


    @GetMapping
    public List<ProductDto> getProducts(@RequestParam(name="categoryId" , required = false) Byte categoryId){
        List<Product> products;
        if(categoryId != null)
            products = productRepository.findByCategoryId(categoryId);
        else
            products = productRepository.findAllWithCategory();
        // findAll first gets product , then for each product
        // get category by id so N+1 problem  so we use
        return products.stream().map(productMapper::toDto).toList();
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id){

        Product product =  productRepository.findById(id).orElse(null);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productMapper.toDto(product));
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto request, UriComponentsBuilder uriBuilder){
        var category = categoryRepository.findById(request.getCategoryId()).orElse(null);
        if(category == null){
            return ResponseEntity.notFound().build();
        }
        var productData = productMapper.toEntity(request);
        productData.setCategory(category);
        productRepository.save(productData);
        request.setId(productData.getId());
        var uri =  uriBuilder.path("/products/{id}").buildAndExpand(productData.getId()).toUri();
        return ResponseEntity.created(uri).body(request);
    }

    @PatchMapping("{id}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto request,@PathVariable Long id){
        var category = categoryRepository.findById(request.getCategoryId()).orElse(null);
        if(category == null){
            return ResponseEntity.notFound().build();
        }
        Product product =  productRepository.findById(id).orElse(null);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        productMapper.update(request,product);
        product.setCategory(category);
        productRepository.save(product);
        request.setId(product.getId());
        return ResponseEntity.ok(request);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        Product product =  productRepository.findById(id).orElse(null);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        productRepository.delete(product);
        return ResponseEntity.noContent().build();
    }








}
