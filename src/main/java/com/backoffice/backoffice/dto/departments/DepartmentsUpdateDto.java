package com.backoffice.backoffice.dto.departments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentsUpdateDto {
    private String beforeName;
    private String afterName;
}
