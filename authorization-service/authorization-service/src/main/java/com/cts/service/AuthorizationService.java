package com.cts.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.entity.Customer;
import com.cts.exception.UserAlreadyExistException;
import com.cts.exception.UserNotFoundException;
import com.cts.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthorizationService implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Customer customer = customerRepository.findByUsername(username);
		if (customer == null) {
			throw new UsernameNotFoundException("Username Not Found");
		}

		return new User(customer.getUsername(), customer.getPassword(), new ArrayList<>());

	}

	public Customer getUser(String username) throws UserNotFoundException {
		Customer customer = customerRepository.findByUsername(username);
		if (customer == null) {
			throw new UserNotFoundException("User Not Found");

		}
		return customer;
	}

	public Customer save(Customer customer) throws UserAlreadyExistException {
		Customer customer2 = customerRepository.findByUsername(customer.getUsername());
		if (customer2 == null) {
			
			Customer customerNew = new Customer();
			customerNew.setName(customer.getName());
			customerNew.setUsername(customer.getUsername());
			customerNew.setPassword(passwordEncoder.encode(customer.getPassword()));
			customerNew.setAddress(customer.getAddress());		
			customerNew.setPan(customer.getPan());
			customerNew.setContactnumber(customer.getContactnumber());
			customerNew.setCountry(customer.getCountry());
			customerNew.setEmail(customer.getEmail());
			customerNew.setAccounttype(customer.getAccounttype());
			customerNew.setDob(customer.getDob());
			
			return customerRepository.save(customerNew);

		}
		throw new UserAlreadyExistException("User Already Exist");
	}

	public Customer getCustomer(String username) throws UserNotFoundException {
		Customer c = customerRepository.findByUsername(username);
		if (c == null) {
			throw new UserNotFoundException("User Not Found");

		}
		return c;
	}

}
