package com.backoffice.backoffice.controller;


import com.backoffice.backoffice.dto.ApiResponse;
import com.backoffice.backoffice.dto.pays.responseDto.PaySalaryResponse;
import com.backoffice.backoffice.dto.pays.responseDto.findSalaryResponse;
import com.backoffice.backoffice.service.PaysService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/Pays")
public class PayController {

    private final PaysService paysService;


    @Operation(summary = "급여 스케줄러 수동 컨트롤러", description = "급여 스케줄러 수동 컨트롤러(건들지 마세요)")
    //스케줄러 수동 컨트롤러
    @PostMapping("/calculate-salary")
    public ApiResponse<String> calculateSalary() {
        try {
            paysService.salary();  // 급여 계산 메서드 호출
            return ApiResponse.success("급여 계산이 성공적으로 완료되었습니다.");
        } catch (Exception e) {
            throw new IllegalStateException("급여 계산중 오류가 발생했습니다.");
        }
    }
    @Operation(summary = "이번달 급여 조회", description = "어느달에 급여를 받았는지 확인가능합니다.")
    @GetMapping("/salary")
    public ResponseEntity<ApiResponse<findSalaryResponse>> findSalaryByMonth(@RequestParam Integer month,HttpSession session) {

        // 월별 급여 정보를 서비스에서 조회
        findSalaryResponse responseDto = paysService.findSalaryByMonth(month, session);

        // ApiResponse로 감싸서 반환
        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }

    @Operation(summary = "받은 모든 급여", description = "입사 후 부터 최근까지 받은 급여")
    @GetMapping("/salaryAll")
    public ResponseEntity<ApiResponse<List<findSalaryResponse>>> findSalaryAll(HttpSession session) {
        List<findSalaryResponse> responseDto = paysService.findSalaryAll(session);

        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }

}
