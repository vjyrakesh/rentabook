package com.rkasibha.rentabook.model;

import com.rkasibha.rentabook.annotation.CityConstraint;
import com.rkasibha.rentabook.annotation.ContactNumberConstraint;
import com.rkasibha.rentabook.annotation.EmailIdConstraint;

import javax.persistence.*;
import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String branchName;

    @NotNull
    @ContactNumberConstraint
    private String contactNumber;

    @NotNull
    @EmailIdConstraint
    private String emailId;

    @NotNull
    @CityConstraint
    private String city;

    @OneToMany(mappedBy = "branch")
    private List<Membership> memberships = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private BranchAdmin admin;

    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<BranchBook> catalog = new HashSet<>();

    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<BookBranchCustomer> allocations = new HashSet<>();

    public Branch() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public List<Membership> getMemberships() {
        return memberships;
    }

    public void setMemberships(List<Membership> memberships) {
        this.memberships = memberships;
    }

    public BranchAdmin getAdmin() {
        return admin;
    }

    public void setAdmin(BranchAdmin admin) {
        this.admin = admin;
    }

    public Set<BranchBook> getCatalog() {
        return catalog;
    }

    public void setCatalog(Set<BranchBook> catalog) {
        this.catalog = catalog;
    }

    public Set<BookBranchCustomer> getAllocations() {
        return allocations;
    }

    public void setAllocations(Set<BookBranchCustomer> allocations) {
        this.allocations = allocations;
    }
}
