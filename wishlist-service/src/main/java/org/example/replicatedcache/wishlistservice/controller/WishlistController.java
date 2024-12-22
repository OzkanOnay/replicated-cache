package org.example.replicatedcache.wishlistservice.controller;

import org.example.replicatedcache.wishlistservice.dto.WishlistDto;
import org.example.replicatedcache.wishlistservice.entity.Wishlist;
import org.example.replicatedcache.wishlistservice.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {

    private final WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping
    public ResponseEntity<Wishlist> createWishlist(@RequestBody Wishlist wishlist) {
        return new ResponseEntity<>(wishlistService.createWishlist(wishlist), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WishlistDto> getWishlistById(@PathVariable Long id) {
        return ResponseEntity.ok(wishlistService.getWishlistById(id));
    }

    @GetMapping
    public ResponseEntity<List<WishlistDto>> getAllWishlists() {
        return ResponseEntity.ok(wishlistService.getAllWishlists());
    }

    // Update a wishlist by ID
    @PutMapping("/{id}")
    public ResponseEntity<Wishlist> updateWishlist(@PathVariable Long id, @RequestBody Wishlist wishlist) {
        return ResponseEntity.ok(wishlistService.updateWishlist(id, wishlist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWishlist(@PathVariable Long id) {
        wishlistService.deleteWishlist(id);
        return ResponseEntity.noContent().build();
    }
}