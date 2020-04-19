package com.rkasibha.rentabook.dto;

import com.rkasibha.rentabook.enumeration.MembershipStatus;

import java.time.LocalDate;

public class MembershipDto {

    private Integer id;
    private LocalDate memberSince;
    private MembershipStatus status;
    private BranchBasicInfoDto branch;
    private CustomerBasicInfoDto customer;

    public MembershipDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getMemberSince() {
        return memberSince;
    }

    public void setMemberSince(LocalDate memberSince) {
        this.memberSince = memberSince;
    }

    public MembershipStatus getStatus() {
        return status;
    }

    public void setStatus(MembershipStatus status) {
        this.status = status;
    }

    public BranchBasicInfoDto getBranch() {
        return branch;
    }

    public void setBranch(BranchBasicInfoDto branch) {
        this.branch = branch;
    }

    public CustomerBasicInfoDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerBasicInfoDto customer) {
        this.customer = customer;
    }
}
