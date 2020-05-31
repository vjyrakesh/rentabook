package com.rkasibha.rentabook.dto;

import com.rkasibha.rentabook.enumeration.MembershipStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerBasicInfoDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private String emailId;
    private LocalDate memberSince;
    private MembershipStatus membershipStatus;
}
