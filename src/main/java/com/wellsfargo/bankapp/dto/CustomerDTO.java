package com.wellsfargo.bankapp.dto;

import java.time.LocalDateTime;

public class CustomerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private int age;
    private String gender;
    private Long mobileNumber;
    private LocalDateTime dateBecameCustomer;

    public CustomerDTO() {}

    public CustomerDTO(
            Long id,
            String firstName,
            String lastName,
            String address,
            String email,
            int age,
            String gender,
            Long mobileNumber,
            LocalDateTime dateBecameCustomer
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.dateBecameCustomer = dateBecameCustomer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setDateBecameCustomer(LocalDateTime dateBecameCustomer) {
        this.dateBecameCustomer = dateBecameCustomer;
    }
}
