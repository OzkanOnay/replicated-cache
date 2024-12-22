package org.example.replicatedcache.catalogservice.repository;

import org.example.replicatedcache.catalogservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
