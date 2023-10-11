package com.app.UserService.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.UserService.Entities.Hotel;
import com.app.UserService.Entities.Rating;
import com.app.UserService.Entities.User;
import com.app.UserService.Exception.ResourceNotFoundException;
import com.app.UserService.ExternalServices.HotelService;
import com.app.UserService.UserService.Uservice;
import com.app.UserService.repositories.UserRepository;

@Service
public class UserServiceImpl implements Uservice {

	@Autowired
	private UserRepository urepo;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HotelService hotelService;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {

		String uid = UUID.randomUUID().toString();
		user.setUserId(uid);
		return urepo.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return urepo.findAll();
	}

	@Override
	public User getUserById(String userId) {
		// TODO Auto-generated method stub
		User user = urepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User with given Id is not found" + userId));
		Rating[] userRating = restTemplate.getForObject(
				"https://Rating_Service/ratings/users/" + user.getUserId(),
				Rating[].class);
		logger.info("{}", userRating);

		List<Rating> ratings = Arrays.stream(userRating).collect(Collectors.toList());

		List<Rating> ratingList = ratings.stream().map(rating -> {
			// ResponseEntity<Hotel> forObject =
			// restTemplate.getForEntity("https://Hotel_Service/hotels/" +
			// rating.getHotelId(), Hotel.class);
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			// Hotel hotel = forObject.getBody();
			// logger.info("respinse status code:{}", forObject.getStatusCode());
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());
		user.setRatings(ratingList);
		return user;
	}

}
