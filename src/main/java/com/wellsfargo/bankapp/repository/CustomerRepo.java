package com.wellsfargo.bankapp.repository;

import com.wellsfargo.bankapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
