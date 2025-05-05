package com.backoffice.backoffice.dto.resigns.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResignsQuitRequest {

    private Integer employeeId;
    private String resignationReason;
//    private boolean isRehired;
}
