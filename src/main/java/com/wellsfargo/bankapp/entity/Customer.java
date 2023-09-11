package com.wellsfargo.bankapp.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

import javax.persistence. *;
@Setter
@Getter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@NonNull
    @Column(name="first_name")
    private String firstName;

	@NonNull
    @Column(name="last_name")
    private String lastName;

	@NonNull
    @Column(name="address")
    private String address;

	@NonNull
    @Column(name="password")
    private String password;

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

	// @OneToMany(mappedBy="user", fetch=FetchType.EAGER, =CascadeType.ALL)
    // private List<Account> accounts;

}


