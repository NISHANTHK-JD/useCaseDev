package com.cts.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse{

	public JwtResponse(String jwttoken, String customerName) {
		super();
		this.jwttoken = jwttoken;
		this.customerName = customerName;
	}
	private String jwttoken;
	private String customerName;
	
	
	public JwtResponse() {
		
	}
	
	public String getJwttoken() {
		return jwttoken;
	}
	public void setJwttoken(String jwttoken) {
		this.jwttoken = jwttoken;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
}
