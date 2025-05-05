package com.backoffice.backoffice.dto.resigns.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResignsQuitResponse {

    private Integer id;
    private String employeeId;
    private String resignationReason;
    private String isRehired;
    private String status;
}
