package com.rkasibha.rentabook.model;

import com.rkasibha.rentabook.enumeration.MembershipStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private LocalDate memberSince;

    @Enumerated(EnumType.STRING)
    private MembershipStatus status;
}
