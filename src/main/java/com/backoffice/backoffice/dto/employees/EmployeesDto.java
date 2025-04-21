package com.backoffice.backoffice.dto.employees;

import com.backoffice.backoffice.service.PhoneNumberService;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
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
    private String phone;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp hireDate;
}
