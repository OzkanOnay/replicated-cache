package org.example.replicatedcache.wishlistservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WishlistServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WishlistServiceApplication.class, args);
	}

}
