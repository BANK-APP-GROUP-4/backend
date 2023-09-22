package com.wellsfargo.bankapp.repository;

import com.wellsfargo.bankapp.entity.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
public interface AdminRepo extends JpaRepository<Admin,Long>{

	 @Query("SELECT a FROM Admin a WHERE a.email = :email")
	    Optional<Admin> findAdminByEmail(@Param("email") String email);
	    
	
	    
}
