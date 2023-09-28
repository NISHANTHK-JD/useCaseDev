package com.example.bankLoanSystem.loanController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankLoanSystem.model.Loan;
import com.example.bankLoanSystem.service.LoanService;


@RestController
@RequestMapping("/loan")
@CrossOrigin(origins = "*")
public class LoanController {
		
		@Autowired
		public LoanService service;
		
		@PutMapping("/update/{id}")
		public ResponseEntity<Loan> updateLoanDetails(@PathVariable int id, @RequestBody Loan loan) {
			return new ResponseEntity<>(service.updateLoan(id, loan), HttpStatus.OK);
		}
		
		@PostMapping("/addloan")
		public ResponseEntity<Loan> addLoan(@RequestBody Loan loan) {
			return new ResponseEntity<>(service.addLoan(loan), HttpStatus.CREATED);
		}

		@GetMapping("/getLoans/{uId}")
		public ResponseEntity<?> getLoans(@RequestBody int uId) throws Exception{
		List<Loan> loans=service.getLoans(uId);
		return new ResponseEntity<List<Loan>>(loans,HttpStatus.OK);
	}
	
	}
