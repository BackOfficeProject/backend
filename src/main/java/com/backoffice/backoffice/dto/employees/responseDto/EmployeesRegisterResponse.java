package com.backoffice.backoffice.dto.employees.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesRegisterResponse {


    private String email;
    private String name;
    private String password;
    private String departmentId;
    private String status;
    private String phone;



}
