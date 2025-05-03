package com.backoffice.backoffice.dto.resigns;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResignsDto {

    private Integer id;
    private Integer employeeId;
    private String resignationReason;
    private boolean isRehired;
}
