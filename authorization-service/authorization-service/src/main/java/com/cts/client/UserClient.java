package com.cts.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cts.entity.Customer;
//import com.cts.entity.User;

@FeignClient(name="User-Details", url="http://localhost:8080/users")
public interface UserClient {
	
	@PostMapping("/adduser")
	public ResponseEntity<Customer> addUser(@RequestBody Customer customer);

}
