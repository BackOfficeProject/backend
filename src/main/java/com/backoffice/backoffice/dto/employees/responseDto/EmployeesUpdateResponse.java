package com.backoffice.backoffice.dto.employees.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeesUpdateResponse {
    private Integer id;
    private String email;
    private String name;
    private String departmentId;
    private String phone;
    private String status;
}
