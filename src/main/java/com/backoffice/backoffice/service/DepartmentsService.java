package com.backoffice.backoffice.service;

import com.backoffice.backoffice.dto.DepartmentsDto;
import com.backoffice.backoffice.exception.CommonExceptionHandler;
import com.backoffice.backoffice.exception.ErrorCode;
import com.backoffice.backoffice.mapper.DepartmentsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentsService {

    private final DepartmentsMapper departmentsMapper;

    //부서 생성
    @Transactional
    public void departmentSave(DepartmentsDto departmentsDto) {
        try {
            departmentsMapper.departmentSave(departmentsDto);
        } catch (Exception e) {
            throw new CommonExceptionHandler(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    //부서 업데이트
    @Transactional
    public void departmentsUpdate(DepartmentsDto departmentsDto) {
        try {
            departmentsMapper.departmentsUpdate(departmentsDto);
        } catch (Exception e) {
            throw new CommonExceptionHandler(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    //부서 삭제
    @Transactional
    public void departmentsDelete(DepartmentsDto departmentsDto) {
        try {
            departmentsMapper.departmentsDelete(departmentsDto);
        } catch (Exception e) {
            throw new CommonExceptionHandler(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    //부서 찾기
    @Transactional
    public List<DepartmentsDto> findByDepartments(String name) {
        return departmentsMapper.findByDepartments(name);
    }

    //모든 부서 출력
    @Transactional
    public List<DepartmentsDto> findAll() {
        return departmentsMapper.findAll();
    }

}
