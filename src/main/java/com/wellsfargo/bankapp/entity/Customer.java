package com.wellsfargo.bankapp.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence. *;

@Entity
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="address")
    private String address;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name="age")
    private int age;

    @Column(name="gender")
    private String gender;

    @Column(name="mobile_number")
    private Long mobileNumber;

	@Column(name="date_became_customer")
	private LocalDate dateBecameCustomer;

    @Column(name="savings_account")
    @OneToMany(mappedBy="customer")
    private List<SavingsAccount> savingsAccountList;

    @Column(name="fd_account")
    @OneToMany(mappedBy="customer")
    private List<FDAccount> fdAccountList;

    public Customer() {
    }

    public Customer(
            String firstName,
            String lastName,
            String address,
            String password,
            String email,
            int age,
            String gender,
            Long mobileNumber,
            LocalDate dateBecameCustomer
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.password = password;
        this.email = email;
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

    public LocalDate getDateBecameCustomer() {
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

    public void setDateBecameCustomer(LocalDate dateBecameCustomer) {
        this.dateBecameCustomer = dateBecameCustomer;
    }

    public List<SavingsAccount> getSavingsAccountList() {
        return savingsAccountList;
    }

    public List<FDAccount> getFdAccountList() {
        return fdAccountList;
    }
}


