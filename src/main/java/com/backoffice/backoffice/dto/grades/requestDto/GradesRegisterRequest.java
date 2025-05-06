package com.backoffice.backoffice.dto.grades.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GradesRegisterRequest {
    private String name;
    private BigDecimal basePay;
}
