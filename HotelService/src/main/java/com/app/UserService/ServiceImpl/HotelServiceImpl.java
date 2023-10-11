package com.app.UserService.ServiceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.UserService.Entity.Hotel;
import com.app.UserService.Exceptions.ResourceNotFoundException;
import com.app.UserService.Repositories.HotelRespository;
import com.app.UserService.Service.HotelService;

public class HotelServiceImpl implements HotelService {

	
	@Autowired
	HotelRespository hRespository;
	
	@Override
	public Hotel create(Hotel hotel) {
		// TODO Auto-generated method stub
		String hid=UUID.randomUUID().toString();
		hotel.setId(hid);
		return hRespository.save(hotel);
	}

	@Override
	public List<Hotel> getAll() {
		// TODO Auto-generated method stub
		return hRespository.findAll();
	}

	@Override
	public Hotel get(String id) {
		// TODO Auto-generated method stub
		return  hRespository.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel not found with Id"+id));
	}
	
	

}
