package com.backoffice.backoffice.dto.departments.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentsUpdateRequest {
    private String beforeName;
    private String afterName;
}
