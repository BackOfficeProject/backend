package com.backoffice.backoffice.dto.pays.requestDto;

import com.backoffice.backoffice.util.NumberFormatSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
public class SalaryDetailRequest {

    @JsonSerialize(using = NumberFormatSerializer.class)
    private BigDecimal bonus; //보너스

    @JsonSerialize(using = NumberFormatSerializer.class)
    private BigDecimal deductions; //공제

    @JsonSerialize(using = NumberFormatSerializer.class)
    private BigDecimal finalPay;//실지급액

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp payDate;

    private String gradeName;
    private String departmentName;

    @JsonSerialize(using = NumberFormatSerializer.class)
    private BigDecimal basePay;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp hireDate;
}
