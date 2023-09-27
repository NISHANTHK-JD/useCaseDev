package com.example.bankLoanSystem.service;

import com.example.bankLoanSystem.model.Loan;


public interface LoanService {

	Loan updateLoan(int id, Loan loan);

	Loan addLoan(Loan loan);

}
