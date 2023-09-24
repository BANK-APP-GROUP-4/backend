package com.wellsfargo.bankapp.dto;

import org.springframework.stereotype.Component;

@Component
public class CustomerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private Long mobileNumber;

    public CustomerDTO() {}

    public CustomerDTO(
            Long id,
            String firstName,
            String lastName,
            String address,
            String email,
            Long mobileNumber
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }
}
