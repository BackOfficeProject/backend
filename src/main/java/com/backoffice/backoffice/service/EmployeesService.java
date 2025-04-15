package com.backoffice.backoffice.service;

import com.backoffice.backoffice.dto.EmployeesDto;
import com.backoffice.backoffice.exception.CommonExceptionHandler;
import com.backoffice.backoffice.exception.ErrorCode;
import com.backoffice.backoffice.mapper.EmployeesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeesService {


    private final EmployeesMapper employeesMapper;

    //사원 가입
    public void employeesSave(EmployeesDto employeesDto) {
        try {
            // 이메일을 찾은 후, 반환된 값이 null이면 새로운 이메일로 저장
            String existingMail = employeesMapper.findByEmail(employeesDto.getEmail());

            // 이메일이 존재하지 않으면 저장
            if (existingMail == null) {
                employeesMapper.employeesSave(employeesDto);
            } else {
                // 이메일이 존재하면 이미 존재하는 메일로 처리
            }
        } catch (Exception e) {
            throw new CommonExceptionHandler(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    //사원 업데이트
    @Transactional
    public void employeesUpdate(EmployeesDto employeesDto) {
        try {
employeesMapper.employeesUpdate(employeesDto);
        } catch (Exception e) {
            throw new CommonExceptionHandler(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    //사원 삭제
    @Transactional
    public void employeesDelete(EmployeesDto employeesDto) {
        try {
employeesMapper.employeesDelete(employeesDto);
        } catch (Exception e) {
            throw new CommonExceptionHandler(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    //사원 찾기
    @Transactional
    public List<EmployeesDto> findEmployees(Integer id) {
        return employeesMapper.findEmployees(id);
    }

    //사원 전체 출력
    @Transactional
    public List<EmployeesDto> findAllEmployees() {
        return employeesMapper.findAllEmployees();
    }
}
