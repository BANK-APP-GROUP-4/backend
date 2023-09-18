package com.wellsfargo.bankapp.repository;

import com.wellsfargo.bankapp.entity.account.FDAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FDAccountRepo extends JpaRepository<FDAccount, Long> {
}