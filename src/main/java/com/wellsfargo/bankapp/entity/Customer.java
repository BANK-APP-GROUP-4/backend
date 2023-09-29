package com.wellsfargo.bankapp.entity;

import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDateTime;

import javax.persistence. *;

@Entity
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(
            generator="customer-id-generator"
    )
    @GenericGenerator(
            name="customer-id-generator",
            strategy="com.wellsfargo.bankapp.generator.CustomerIdGenerator"
    )
    private Long id;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="address")
    private String address;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;
    @Column(name="age")
    private int age;
    @Column(name="gender")
    private String gender;
    @Column(name="mobile_number")
    private Long mobileNumber;
	@Column(name="date_became_customer")
	private LocalDateTime dateBecameCustomer;

    public Customer() {}

    public void setId(Long id) {
		this.id = id;
	}

	public Customer(
            String firstName,
            String lastName,
            String address,
            String email,
            String password,
            int age,
            String gender,
            Long mobileNumber,
            LocalDateTime dateBecameCustomer
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.dateBecameCustomer = dateBecameCustomer;
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

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public LocalDateTime getDateBecameCustomer() {
        return dateBecameCustomer;
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

    public void setPassword(String password) {
        this.password = password;
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


