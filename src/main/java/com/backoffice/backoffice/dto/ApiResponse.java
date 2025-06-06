package com.backoffice.backoffice.dto;

import com.backoffice.backoffice.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
    private final int status;     // HTTP 상태 코드
    private final String message; // 메시지
    private final T data;         // 응답 데이터 (성공 시)
    private final ErrorCode code; // 에러 코드 (실패 시)
    private String timestamp;



    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(
                200,
                "요청 성공",
                data,
                null,
                LocalDateTime.now().toString());
    }

    public static <T> ApiResponse<T> error(ErrorCode errorCode) {
        return new ApiResponse<>(
                errorCode.getStatus().value(),
                errorCode.getMessage(),
                null,
                errorCode,
                LocalDateTime.now().toString());
    }

}
