package org.example.replicatedcache.wishlistservice.repository;

import org.example.replicatedcache.wishlistservice.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
}
