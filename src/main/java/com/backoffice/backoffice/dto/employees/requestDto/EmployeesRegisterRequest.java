package com.backoffice.backoffice.dto.employees.requestDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesRegisterRequest {

    @Schema(hidden = true)
    private Integer id;

    private String email;
    private String name;
    private String password;
    private Integer departmentId;
//    private boolean status;
    private String phone;



}
