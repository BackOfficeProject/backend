package com.backoffice.backoffice.service;

import com.backoffice.backoffice.dto.departments.requestDto.DepartmentsDeleteRequest;
import com.backoffice.backoffice.dto.departments.requestDto.DepartmentsDto;
import com.backoffice.backoffice.dto.departments.requestDto.DepartmentsRegisterRequest;
import com.backoffice.backoffice.dto.departments.requestDto.DepartmentsUpdateRequest;
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
    public void departmentSave(DepartmentsRegisterRequest departmentsRegisterRequest) {
        try {
            departmentsMapper.departmentSave(departmentsRegisterRequest);
        } catch (Exception e) {
            throw new CommonExceptionHandler(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    //부서 업데이트
    @Transactional
    public DepartmentsUpdateRequest departmentsUpdate(DepartmentsUpdateRequest departmentsUpdateRequest) {
            departmentsMapper.departmentsUpdate(departmentsUpdateRequest);
        return departmentsUpdateRequest;
    }

    //부서 삭제
    @Transactional
    public DepartmentsDeleteRequest departmentsDelete(String name) {
        try {
            DepartmentsDeleteRequest departmentsDeleteRequest = new DepartmentsDeleteRequest();
            departmentsDeleteRequest.setName(name);
            departmentsMapper.departmentsDelete(departmentsDeleteRequest);

            return departmentsDeleteRequest;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonExceptionHandler(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    //부서 찾기
    @Transactional
    public List<DepartmentsDto> findByDepartments(String name) {
        return departmentsMapper.findByDepartments(name);
    }

    //부서 아이디 찾기
    @Transactional
    public DepartmentsDto findId(Integer id) {
        return departmentsMapper.findId(id);
    }

    //부서 아이디 찾기
    @Transactional
    public DepartmentsDto findName(Integer id) {
        return departmentsMapper.findId(id);
    }

    //모든 부서 출력
    @Transactional
    public List<DepartmentsDto> findAll() {
        return departmentsMapper.findAll();
    }

}
