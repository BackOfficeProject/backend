package com.backoffice.backoffice.dto.employees;

import com.backoffice.backoffice.service.PhoneNumberService;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

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
