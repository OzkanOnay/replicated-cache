package org.example.replicatedcache.wishlistservice.service.impl;

import com.hazelcast.core.HazelcastInstance;
import org.example.replicatedcache.wishlistservice.constant.ProductConstant;
import org.example.replicatedcache.wishlistservice.dto.WishlistDto;
import org.example.replicatedcache.wishlistservice.entity.Wishlist;
import org.example.replicatedcache.wishlistservice.repository.WishlistRepository;
import org.example.replicatedcache.wishlistservice.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final HazelcastInstance hazelcastInstance;

    @Autowired
    public WishlistServiceImpl(WishlistRepository wishlistRepository, HazelcastInstance hazelcastInstance) {
        this.wishlistRepository = wishlistRepository;
        this.hazelcastInstance = hazelcastInstance;
    }

    @Override
    public Wishlist createWishlist(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    @Override
    public WishlistDto getWishlistById(Long id) {
        return mapToWishlistDto(findWishlistById(id));
    }

    @Override
    public List<WishlistDto> getAllWishlists() {
        return wishlistRepository.findAll()
                .stream()
                .map(this::mapToWishlistDto)
                .toList();
    }

    @Override
    public Wishlist updateWishlist(Long id, Wishlist wishlist) {
        Wishlist existingWishlist = findWishlistById(id);
        existingWishlist.setUserId(wishlist.getUserId());
        existingWishlist.setProductId(wishlist.getProductId());
        return wishlistRepository.save(existingWishlist);
    }

    @Override
    public void deleteWishlist(Long id) {
        wishlistRepository.deleteById(id);
    }

    private Wishlist findWishlistById(Long id) {
        return wishlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));
    }

    private WishlistDto mapToWishlistDto(Wishlist wishlist) {
        var productMap = hazelcastInstance.getReplicatedMap(ProductConstant.PRODUCTS);
        var productDescription = productMap.getOrDefault(wishlist.getProductId(), "");

        return new WishlistDto(
                wishlist.getId(),
                wishlist.getProductId(),
                productDescription.toString()
        );
    }
}