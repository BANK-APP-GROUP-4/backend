package com.wellsfargo.bankapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.bankapp.entity.Admin;
import com.wellsfargo.bankapp.entity.Customer;
import com.wellsfargo.bankapp.entity.Transaction;
import com.wellsfargo.bankapp.entity.account.SavingsAccount;
import com.wellsfargo.bankapp.repository.AdminRepo;
import com.wellsfargo.bankapp.repository.CustomerRepo;
import com.wellsfargo.bankapp.repository.SavingsAccountRepo;
import com.wellsfargo.bankapp.repository.TransactionRepo;

@Service
public class AdminService {
	@Autowired
	AdminRepo adminRepo;
	
	@Autowired
	SavingsAccountRepo accRepo;
	
	@Autowired 
	CustomerService customerService;
	
	@Autowired
	CustomerRepo customerRepo;
	
	@Autowired
	TransactionRepo transactionRepo;
	
	@Autowired 
	SavingsAccountService savingsAccountService;
	
	public String saveAdmin(Admin admin)
	{
		String result = "";
		Optional<Admin> o = adminRepo.findAdminByEmail(admin.getEmail());
		if(o.isPresent())
		{
			result = "Admin already exists!";
		}
		else {
			
			Admin obj = adminRepo.save(admin);
			result = "Admin created successfully!";
		}
		return result;
	}
	
	public String adminLogin(Admin user)
	{
		Admin admin = null;
		String result = "";
		
		Optional<Admin> o = adminRepo.findAdminByEmail(user.getEmail());
		
		if(o.isPresent())
		{
			admin = o.get();
		}
		if(admin == null)
		{
			result = "Invalid Admin";
		}
		else
		{
			if(user.getPassword().equals(admin.getPassword()))
			{
				result = "Login success";
			}
			else
			{
				result = "Login failed";
			}
		}
		return result;
	}
	
	
	public String disableAccount(long accno)
	{
		if(!accRepo.findById(accno).isPresent())
			return "Account not found";
		SavingsAccount acc = accRepo.findById(accno).get();
			if(acc.getBalance() < 10000)
			{
				acc.setAccountStatus(false);
				accRepo.save(acc);
				return "Account Disabled";
			}
			else
				return "Account cannot be disabled";
	}
	
	public String activateAccount(long accno)
	{
		if(!accRepo.findById(accno).isPresent())
			return "Account not found";
		SavingsAccount acc = accRepo.findById(accno).get();
			if(acc.getBalance() >= 10000)
			{
				acc.setAccountStatus(true);
				accRepo.save(acc);
				return "Account Reactivated";
			}
			else
				return "Account cannot be activated due to low balance";
		
	}
	public List<Customer> getCustomers(){
		return customerRepo.findAll();
		
	}
	
	public List<Transaction> getTransactions(){
		return transactionRepo.findAll();
		
	}
	
	public String updateBalance(long accNumber,long amount) {
		
		SavingsAccount account = null;
		SavingsAccount sa =savingsAccountService.findSavingsAccountByIdInternal(accNumber);
		if(sa!=null) {
			account=sa;
		}else {
			return "Account Not Found.";
			
		}
		account.setBalance(amount);
		accRepo.save(account);
		return "Account Balance updated succesfully!";
		
	}
	public List<SavingsAccount> getAccounts(){
		return accRepo.findAll();
		
	}
	
}
