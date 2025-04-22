package com.backoffice.backoffice.dto.employees;

import com.backoffice.backoffice.service.PhoneNumberService;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesUpdateDto {

    private Integer id;
    private String email;
    private String name;
    private Integer departmentId;
    private boolean status;
    private String phone;



}
