package com.rkasibha.rentabook.model;

import com.rkasibha.rentabook.annotation.ContactNumberConstraint;
import com.rkasibha.rentabook.annotation.EmailIdConstraint;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotNull;

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

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", branchName='" + branchName + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", emailId='" + emailId + '\'' +
                '}';
    }
}
