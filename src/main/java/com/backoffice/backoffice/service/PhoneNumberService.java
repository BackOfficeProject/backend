package com.backoffice.backoffice.service;

import org.springframework.stereotype.Service;

@Service
public class PhoneNumberService {

    /**
     * 전화번호를 항상 하이픈 포함 형식(010-1234-5678)으로 반환한다.
     * 입력값이 01012345678 또는 010-1234-5678 등 다양한 형식이어도 정상 처리함.
     */
    public static String formatPhone(String phone) {
        if (phone == null) {
            throw new IllegalArgumentException("전화번호는 null일 수 없습니다.");
        }

        // 숫자만 추출
        String digitsOnly = phone.replaceAll("\\D", "");

        // 전화번호가 010으로 시작하고 총 11자리여야 함
        if (!digitsOnly.matches("^010\\d{8}$")) {
            throw new IllegalArgumentException("유효하지 않은 전화번호입니다. '01012345678' 또는 '010-1234-5678' 형식이어야 합니다.");
        }

        // 010-1234-5678 형식으로 하이픈 삽입
        return digitsOnly.replaceFirst("(\\d{3})(\\d{4})(\\d{4})", "$1-$2-$3");
    }

    /**
     * 유효한 전화번호인지 검사 (010으로 시작하고 11자리 숫자)
     */
    public static boolean isValidPhoneFormat(String phone) {
        if (phone == null) return false;
        String digitsOnly = phone.replaceAll("\\D", "");
        return digitsOnly.matches("^010\\d{8}$");
    }

    /**
     * 전화번호 파싱 메서드 - 유효성 검사 후 하이픈 포함 포맷 반환
     */
    public static String parsePhone(String phone) {
        return formatPhone(phone); // 내부적으로 유효성 검사 포함
    }
}
