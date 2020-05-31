package com.rkasibha.rentabook.model;

import com.rkasibha.rentabook.annotation.CityConstraint;
import com.rkasibha.rentabook.annotation.ContactNumberConstraint;
import com.rkasibha.rentabook.annotation.CountryConstraint;
import com.rkasibha.rentabook.annotation.EmailIdConstraint;
import com.rkasibha.rentabook.enumeration.MembershipStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @ContactNumberConstraint
    private String contactNumber;

    @NotNull
    @EmailIdConstraint
    private String emailId;

    @NotNull
    @CityConstraint
    private String city;

    @NotNull
    @CountryConstraint
    private String country;

    private LocalDate memberSince;

    private LocalDate memberTill;

    @Enumerated(EnumType.STRING)
    private MembershipStatus status;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<BookBranchCustomer> allocations = new HashSet<>();


    public Customer() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Set<BookBranchCustomer> getAllocations() {
        return allocations;
    }

    public void setAllocations(Set<BookBranchCustomer> allocations) {
        this.allocations = allocations;
    }

    public LocalDate getMemberSince() {
        return memberSince;
    }

    public void setMemberSince(LocalDate memberSince) {
        this.memberSince = memberSince;
    }

    public LocalDate getMemberTill() {
        return memberTill;
    }

    public void setMemberTill(LocalDate memberTill) {
        this.memberTill = memberTill;
    }

    public MembershipStatus getStatus() {
        return status;
    }

    public void setStatus(MembershipStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", emailId='" + emailId + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", memberSince=" + memberSince +
                ", memberTill=" + memberTill +
                ", status=" + status +
                '}';
    }
}
