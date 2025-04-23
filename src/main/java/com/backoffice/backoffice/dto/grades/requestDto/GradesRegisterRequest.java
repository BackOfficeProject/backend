package com.backoffice.backoffice.dto.grades.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GradesRegisterRequest {
    private String name;
}
