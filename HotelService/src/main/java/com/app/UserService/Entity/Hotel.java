package com.app.UserService.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Hotel")
public class Hotel {
	
	@Id
	private String id;
	private String name;
	private String location;
	private String about;

}