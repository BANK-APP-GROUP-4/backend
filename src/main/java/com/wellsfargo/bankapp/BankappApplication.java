package com.wellsfargo.bankapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.wellsfargo.bankapp.entity.Customer;
import com.wellsfargo.bankapp.entity.Transaction;
import com.wellsfargo.bankapp.entity.account.FDAccount;
import com.wellsfargo.bankapp.entity.account.SavingsAccount;
import com.wellsfargo.bankapp.repository.CustomerRepo;
import com.wellsfargo.bankapp.repository.FDAccountRepo;
import com.wellsfargo.bankapp.repository.SavingsAccountRepo;
import com.wellsfargo.bankapp.repository.TransactionRepo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@EnableScheduling
public class BankappApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankappApplication.class, args);
	}

	public static class CustomersObj {
		private List<Customer> customers;
		public List<Customer> getCustomers() {
			return customers;
		}
		public void setCustomers(List<Customer> customers) {
			this.customers = customers;
		}
	}

	@Bean
	CommandLineRunner commandLineRunner(
			CustomerRepo customerRepo,
			SavingsAccountRepo savingsRepo,
			FDAccountRepo fdAccountRepo,
			TransactionRepo transactionRepo
	) {
		return args -> {
			List<Customer> customers = null;
			CustomersObj customersObj = null;
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			try{
				customersObj = objectMapper.readValue(
						new File("src/main/resources/data/customers.json"), CustomersObj.class
				);
			}
			catch (IOException e) {
				e.printStackTrace();
			}

			customers = customersObj.getCustomers();
			for(Customer customer : customers){
				customerRepo.save(customer);
				savingsRepo.save(new SavingsAccount(customer, customer.getDateBecameCustomer(), customer.getDateBecameCustomer(), 10000, true, true, false));
				fdAccountRepo.save(new FDAccount(customer, customer.getDateBecameCustomer(), 100000, 3, false));
			}

			Random random = new Random();
			List<SavingsAccount> savingsAccounts = savingsRepo.findAll();

			for(int i = 0; i < 50; i++){
				SavingsAccount senderAcc = savingsAccounts.get(random.nextInt(savingsAccounts.size()));
				SavingsAccount receiverAcc = savingsAccounts.get(random.nextInt(savingsAccounts.size()));

				long minDay = LocalDate.of(2022, 1, 1).toEpochDay();
				long maxDay = LocalDate.of(2023, 9, 29).toEpochDay();
				long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
				LocalDate randomDate = LocalDate.ofEpochDay(randomDay);

				transactionRepo.save(new Transaction(senderAcc, receiverAcc, 150, randomDate, "VALID"));
			}
		};
	}
}
