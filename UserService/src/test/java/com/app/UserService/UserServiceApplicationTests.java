package com.app.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.UserService.ExternalServices.RatingService;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;

//	void createRating() {
//		Rating rating = Rating.builder().rating(10).userId("").hotelId("")
//				.feedback("This is created using feign client").build();
//		ResponseEntity<Rating> savedRating = ratingService.createRating(rating);
//		
//		System.out.println("new Rating created");
//
//	}
}
