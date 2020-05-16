package com.rkasibha.rentabook.dto;

import lombok.Data;

@Data
public class BranchDto {
    private Integer id;
    private String branchName;
    private String contactNumber;
    private String emailId;
    private String city;
    private BranchAdminDto admin;
}
