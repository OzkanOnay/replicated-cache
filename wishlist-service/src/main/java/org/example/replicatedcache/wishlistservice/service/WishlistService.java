package org.example.replicatedcache.wishlistservice.service;

import org.example.replicatedcache.wishlistservice.dto.WishlistDto;
import org.example.replicatedcache.wishlistservice.entity.Wishlist;
import java.util.List;

public interface WishlistService {
    Wishlist createWishlist(Wishlist wishlist);
    WishlistDto getWishlistById(Long id);
    List<WishlistDto> getAllWishlists();
    Wishlist updateWishlist(Long id, Wishlist wishlist);
    void deleteWishlist(Long id);
}