package org.example.replicatedcache.catalogservice.service;

import org.example.replicatedcache.catalogservice.dto.ProductCreateDto;
import org.example.replicatedcache.catalogservice.dto.ProductReadDto;

import java.util.List;
import org.example.replicatedcache.catalogservice.dto.ProductUpdateDto;

public interface ProductService {
    ProductReadDto create(ProductCreateDto createDto);
    List<ProductReadDto> getAll();
    ProductReadDto getById(Long id);
    ProductReadDto update(Long id, ProductUpdateDto updateDto);
    void delete(Long id);
}