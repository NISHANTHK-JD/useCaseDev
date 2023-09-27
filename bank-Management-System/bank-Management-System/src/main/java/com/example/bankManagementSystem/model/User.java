package com.example.bankManagementSystem.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "User_Table")

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	private String Name;
	private String userName;
	private String password;
	private String address;
	private String pan;
	private Long contactNumber;
	private String country;
	private String email;
	private String accountType;
	private Date dob;
	
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public Long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public User(String name, String userName, String password, String address, String pan, Long contactNumber,
			String country, String email, String accountType, Date dob) {
		super();
		Name = name;
		this.userName = userName;
		this.password = password;
		this.address = address;
		this.pan = pan;
		this.contactNumber = contactNumber;
		this.country = country;
		this.email = email;
		this.accountType = accountType;
		this.dob = dob;
	}
	@Override
	public String toString() {
		return "User [Name=" + Name + ", userName=" + userName + ", password=" + password + ", address=" + address
				+ ", pan=" + pan + ", contactNumber=" + contactNumber + ", country=" + country + ", email=" + email
				+ ", accountType=" + accountType + ", dob=" + dob + "]";
	}
	public User() {
		super();

	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	

}
