package com.example.bankLoanSystem.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "Loan_Table")

@Entity
public class Loan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loanId;
	private String loanType;
	private long loanAmount;
	private Date date;
	private int rateOfInterest;
	private int durationOfLoan;
	private int userid;

	public Loan(int loanId, String loanType, long loanAmount, Date date, int rateOfInterest, int durationOfLoan, int userid) {
		super();
		this.loanId = loanId;
		this.loanType = loanType;
		this.loanAmount = loanAmount;
		this.date = date;
		this.rateOfInterest = rateOfInterest;
		this.durationOfLoan = durationOfLoan;
		this.userid = userid;
	}

	public Loan() {
		super();
	}

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public long getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(long loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getRateOfInterest() {
		return rateOfInterest;
	}

	public void setRateOfInterest(int rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}

	public int getDurationOfLoan() {
		return durationOfLoan;
	}

	public void setDurationOfLoan(int durationOfLoan) {
		this.durationOfLoan = durationOfLoan;
	}

	public int getUserId() {
		return userid;
	}

	public void setUserId(int userid) {
		this.userid = userid;
	}
// toString to be used.
	}

}
