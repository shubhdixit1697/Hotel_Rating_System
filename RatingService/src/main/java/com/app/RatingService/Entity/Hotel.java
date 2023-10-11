package com.app.RatingService.Entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("Hotel")
public class Hotel {
	
	
	private String id;
	private String name;
	private String location;
	private String about;

}