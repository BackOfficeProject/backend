package com.backoffice.backoffice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentsDto {
    private Integer departmentId;
    private String name;

    private String beforeName;
    private String afterName;


}
