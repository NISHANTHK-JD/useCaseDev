package com.example.bankManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bankManagementSystem.model.User;

@Repository
public interface UserAccountRepository extends JpaRepository<User, Integer>{
	

}
