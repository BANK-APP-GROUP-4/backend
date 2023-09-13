package com.wellsfargo.bankapp.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

import javax.persistence. *;

import org.springframework.web.bind.annotation.CrossOrigin;
@Setter
@Getter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="customer")
public class Customer {
    public LocalDate getDateBecameCustomer() {
		return dateBecameCustomer;
	}

	public void setDateBecameCustomer(LocalDate dateBecameCustomer) {
		this.dateBecameCustomer = dateBecameCustomer;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@NonNull
    @Column(name="first_name")
    private String firstName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NonNull
    @Column(name="last_name")
    private String lastName;
	public Customer(@NonNull String firstName, @NonNull String lastName, @NonNull String address,
			@NonNull String password, @NonNull String email, @NonNull int age, @NonNull String gender,
			@NonNull Long mobileNumber, @NonNull LocalDate dateBecameCustomer) {
		super();
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
	
	public Customer() {}

	@NonNull
    @Column(name="address")
    private String address;

	@NonNull
    @Column(name="password")
    private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NonNull
    @Column(name="email")
    private String email;

	@NonNull
    @Column(name="age")
    private int age;

	@NonNull
    @Column(name="gender")
    private String gender;

	@NonNull
    @Column(name="mobile_number")
    private Long mobileNumber;

	@NonNull
	@Column(name="date_became_customer")
	private LocalDate dateBecameCustomer;

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", password=" + password + ", email=" + email + ", age=" + age + ", gender=" + gender
				+ ", mobileNumber=" + mobileNumber + ", dateBecameCustomer=" + dateBecameCustomer + "]";
	}

	// @OneToMany(mappedBy="user", fetch=FetchType.EAGER, =CascadeType.ALL)
    // private List<Account> accounts;
	
	

}


