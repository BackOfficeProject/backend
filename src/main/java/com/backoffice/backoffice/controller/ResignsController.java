package com.backoffice.backoffice.controller;

import com.backoffice.backoffice.dto.ApiResponse;
import com.backoffice.backoffice.dto.resigns.requestDto.ResignsQuitRequest;
import com.backoffice.backoffice.dto.resigns.responseDto.ResignsQuitResponse;
import com.backoffice.backoffice.service.ResignsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/Resigns")
public class ResignsController {

    private final ResignsService resignsService;

    @Operation(summary = "퇴사", description = "퇴사를 합니다.")
    @PostMapping
    public ResponseEntity<ApiResponse<ResignsQuitResponse>> quit(@RequestBody ResignsQuitRequest resignsQuitRequest) {
        ResignsQuitResponse responseDto = resignsService.quit(resignsQuitRequest);

        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }
}
