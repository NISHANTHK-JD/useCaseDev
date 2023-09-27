package com.example.bankManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankManagementSystem.exception.UserException;
import com.example.bankManagementSystem.model.User;
import com.example.bankManagementSystem.repository.UserAccountRepository;

@Service
public class UserAccountServiceImpl implements UserAccountService {

	@Autowired
	public UserAccountRepository repo;

	@Override
	public User updateUser(int id, User user) {
		User upUser = repo.findById(id).orElseThrow(() -> new UserException("User not found"));
		
		upUser.setName(user.getName());
		upUser.setUserName(user.getUserName());
		upUser.setPassword(user.getPassword());
		upUser.setAddress(user.getAddress());
		upUser.setContactNumber(user.getContactNumber());
		upUser.setCountry(user.getCountry());
		upUser.setEmail(user.getEmail());
		upUser.setDob(user.getDob());
		upUser.setPan(user.getPan());

		return repo.save(upUser);
	}

	@Override
	public User addUser(User user) {
		return repo.save(user);
	}

}
