package com.backoffice.backoffice.dto.employees;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesJoinResponseDto {


    private String email;
    private String name;
    private String password;
    private String departmentId;
    private String status;
    private String phone;



}
