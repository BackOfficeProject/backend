package com.backoffice.backoffice.dto.pays;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaysDto {
    private Integer id;
    private Integer employeesId;
    private BigDecimal basePay; //기본급
    private BigDecimal bonus; //보너스
    private BigDecimal deductions; //공제
    private BigDecimal finalPay;
}
