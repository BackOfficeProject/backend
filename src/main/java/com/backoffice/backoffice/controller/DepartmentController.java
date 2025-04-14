package com.backoffice.backoffice.controller;

import com.backoffice.backoffice.dto.ApiResponse;
import com.backoffice.backoffice.dto.DepartmentsDto;
import com.backoffice.backoffice.service.DepartmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DepartmentController {

    private final DepartmentsService departmentsService;


    @PostMapping("/departments/join")
    public ResponseEntity<ApiResponse<DepartmentsDto>> departmentSave(@RequestBody DepartmentsDto departmentsDto) {
            departmentsService.departmentSave(departmentsDto);
        return ResponseEntity.ok(ApiResponse.success(departmentsDto));
    }

}
