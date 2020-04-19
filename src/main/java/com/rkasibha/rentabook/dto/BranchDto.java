package com.rkasibha.rentabook.dto;

public class BranchDto {
    private Integer id;
    private String branchName;
    private String contactNumber;
    private String emailId;
    private String city;
    private BranchAdminDto branchAdmin;

    public BranchDto() {
    }

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

    public BranchAdminDto getBranchAdmin() {
        return branchAdmin;
    }

    public void setBranchAdmin(BranchAdminDto branchAdmin) {
        this.branchAdmin = branchAdmin;
    }
}
