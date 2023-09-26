package com.wellsfargo.bankapp.repository;

import com.wellsfargo.bankapp.entity.account.FDAccount;
import com.wellsfargo.bankapp.entity.account.SavingsAccount;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FDAccountRepo extends JpaRepository<FDAccount, Long> {
	
	@Query("SELECT f FROM FDAccount f WHERE f.customer.id=:cust_id")
    List<FDAccount> findByCustId(Long cust_id);
}