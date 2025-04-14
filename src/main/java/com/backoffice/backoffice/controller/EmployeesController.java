package com.backoffice.backoffice.controller;

import com.backoffice.backoffice.dto.EmployeesDto;
import com.backoffice.backoffice.service.EmployeesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmployeesController {
    private final EmployeesService employeesService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody EmployeesDto employeesDto) {
        try {
            employeesService.save(employeesDto);
            return ResponseEntity.ok("사원 가입이 완료되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다");
        }
    }
}
