package com.backoffice.backoffice.dto.pays.requestDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaySalaryRequest {
    private Integer id;
    private Integer employeeId;
    private BigDecimal bonus; //보너스
    private BigDecimal deductions; //공제
    private BigDecimal finalPay;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp payDate;
}
