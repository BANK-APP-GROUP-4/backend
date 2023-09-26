package com.wellsfargo.bankapp.repository;

import com.wellsfargo.bankapp.entity.Customer;
import com.wellsfargo.bankapp.entity.account.SavingsAccount;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SavingsAccountRepo extends JpaRepository<SavingsAccount, Long> {

	@Query("SELECT a FROM SavingsAccount a WHERE a.customer.id=:cust_id")
    Optional<SavingsAccount> findByCustId(Long cust_id);
		
}
