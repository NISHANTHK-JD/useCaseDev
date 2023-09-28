package com.example.bankLoanSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bankLoanSystem.model.Loan;

@Repository
public interface LoanRepository  extends JpaRepository<Loan, Integer>{

	public List<Loan> findByUserId(int userid);
	
}
