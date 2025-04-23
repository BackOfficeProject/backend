package com.backoffice.backoffice.dto.employees;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesJoinDto {


    private String email;
    private String name;
    private String password;
    private Integer departmentId;
    private boolean status;
    private String phone;



}
