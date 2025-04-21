package com.backoffice.backoffice.dto.employees;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeesUpdateResponseDto {
    private Integer id;
    private String email;
    private String name;
    private String departmentId;
    private String phone;
    private String status;
}
