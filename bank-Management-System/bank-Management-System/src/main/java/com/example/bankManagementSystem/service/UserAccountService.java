package com.example.bankManagementSystem.service;

import com.example.bankManagementSystem.model.User;


public interface UserAccountService {

	User updateUser(int id, User user);

	User addUser(User user);

}
