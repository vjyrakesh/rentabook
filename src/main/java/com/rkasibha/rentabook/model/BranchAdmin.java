package com.rkasibha.rentabook.model;

import com.rkasibha.rentabook.annotation.ContactNumberConstraint;
import com.rkasibha.rentabook.annotation.EmailIdConstraint;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class BranchAdmin {

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

    @OneToOne(mappedBy = "admin")
    private Branch branch;

    public BranchAdmin() {}

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

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String toString() {
        return "BranchAdmin{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", emailId='" + emailId + '\'' +
                '}';
    }
}
