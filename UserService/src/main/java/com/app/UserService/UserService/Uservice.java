package com.app.UserService.UserService;

import java.util.List;

import com.app.UserService.Entities.User;

public interface Uservice {

	User saveUser(User user);

	List<User> getAllUser();

	User getUserById(String userId);

}
