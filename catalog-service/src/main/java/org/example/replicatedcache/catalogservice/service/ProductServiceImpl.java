package org.example.replicatedcache.catalogservice.service;

import com.hazelcast.core.HazelcastInstance;
import org.example.replicatedcache.catalogservice.constant.ProductConstant;
import org.example.replicatedcache.catalogservice.dto.ProductCreateDto;
import org.example.replicatedcache.catalogservice.dto.ProductReadDto;
import org.example.replicatedcache.catalogservice.dto.ProductUpdateDto;
import org.example.replicatedcache.catalogservice.entity.Product;
import org.example.replicatedcache.catalogservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final HazelcastInstance hazelcastInstance;

    public ProductServiceImpl(ProductRepository productRepository, HazelcastInstance hazelcastInstance) {
        this.productRepository = productRepository;
        this.hazelcastInstance = hazelcastInstance;
    }

    @Override
    public ProductReadDto create(ProductCreateDto createDto) {
        var entity = new Product();
        entity.setDescription(createDto.description());
        entity.setName(createDto.name());
        Product savedEntity = productRepository.save(entity);

        addToCache(savedEntity);

        return mapToReadDto(savedEntity);
    }

    @Override
    public List<ProductReadDto> getAll() {
        return productRepository.findAll().stream()
                .map(this::mapToReadDto)
                .toList();
    }

    @Override
    public ProductReadDto getById(Long id) {
        return mapToReadDto(findProductById(id));
    }

    @Override
    public ProductReadDto update(Long id, ProductUpdateDto updateDto) {
        Product entity = findProductById(id);
        entity.setDescription(updateDto.description());
        entity.setName(updateDto.name());
        Product updatedEntity = productRepository.save(entity);

        addToCache(updatedEntity);

        return mapToReadDto(updatedEntity);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    private Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity not found"));
    }

    private ProductReadDto mapToReadDto(Product entity) {
        return new ProductReadDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription()
        );
    }

    private void addToCache(Product entity) {
        var productMap = hazelcastInstance.getReplicatedMap(ProductConstant.PRODUCTS);
        productMap.put(entity.getId(), entity.getDescription());

    }
}