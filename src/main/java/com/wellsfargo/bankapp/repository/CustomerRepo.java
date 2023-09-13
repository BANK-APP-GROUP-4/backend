package com.wellsfargo.bankapp.repository;

import com.wellsfargo.bankapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE c.email = :email")
    Optional<Customer> findCustomerByEmail(@Param("email") String email);
    
    @Query("SELECT c FROM Customer c WHERE c.firstName = :firstName")
    Optional<Customer> findCustomerByfname(@Param("firstName") String firstName);

    @Query("SELECT c FROM Customer c WHERE c.mobileNumber = :mobileNumber")
    Optional<Customer> findCustomerByMobileNumber(@Param("mobileNumber") Long mobileNumber);
}
