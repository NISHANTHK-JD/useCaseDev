package com.example.bankLoanSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankLoanSystem.exception.LoanException;
import com.example.bankLoanSystem.model.Loan;
import com.example.bankLoanSystem.repository.LoanRepository;


@Service
public class LoanServiceImpl implements LoanService{
	
	@Autowired
	public LoanRepository repo;

	@Override
	public Loan updateLoan(int id, Loan loan) {
		Loan upLoan = repo.findById(id).orElseThrow(() -> new LoanException("User not found"));
		upLoan.setLoanId(loan.getLoanId());
		upLoan.setDate(loan.getDate());
		upLoan.setLoanAmount(loan.getLoanAmount());
		upLoan.setLoanType(loan.getLoanType());
		upLoan.setRateOfInterest(loan.getRateOfInterest());
		upLoan.setDurationOfLoan(loan.getDurationOfLoan());
		return repo.save(upLoan);
	}

	@Override
	public Loan addLoan(Loan loan) {
		return repo.save(loan);
	}

	@Override
	public List<Loan> getLoans(int userid) {
		return repo.findByUserId(userid);
	}


}
