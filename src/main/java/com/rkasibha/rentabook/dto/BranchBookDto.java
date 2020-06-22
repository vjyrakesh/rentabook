package com.rkasibha.rentabook.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class BranchBookDto {
    private Set<BranchBookAddDto> books = new HashSet<>();
}
