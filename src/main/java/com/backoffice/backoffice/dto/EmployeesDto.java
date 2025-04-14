package com.backoffice.backoffice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeesDto {
    private Integer id;
    private String email;
    private String name;
    private String password;
    private Integer departmentId;
    private boolean status;
    private Integer phone;
    private Timestamp hireDate;
}
