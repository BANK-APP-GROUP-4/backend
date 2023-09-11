package com.wellsfargo.bankapp.repository;

import com.wellsfargo.bankapp.entity.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsAccountRepo extends JpaRepository<SavingsAccount, Long> {
}
