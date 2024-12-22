package org.example.replicatedcache.catalogservice.controller;

import org.example.replicatedcache.catalogservice.dto.ProductCreateDto;
import org.example.replicatedcache.catalogservice.dto.ProductReadDto;
import org.example.replicatedcache.catalogservice.dto.ProductUpdateDto;
import org.example.replicatedcache.catalogservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductReadDto createProduct(@RequestBody ProductCreateDto createDto) {
        return productService.create(createDto);
    }

    @GetMapping
    public List<ProductReadDto> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ProductReadDto getProductById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PutMapping("/{id}")
    public ProductReadDto updateProduct(
            @PathVariable Long id,
            @RequestBody ProductUpdateDto updateDto
    ) {
        return productService.update(id, updateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }
}