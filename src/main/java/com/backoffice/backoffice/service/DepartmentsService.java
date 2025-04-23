package com.backoffice.backoffice.service;

import com.backoffice.backoffice.dto.departments.DepartmentsDeleteDto;
import com.backoffice.backoffice.dto.departments.DepartmentsDto;
import com.backoffice.backoffice.dto.departments.DepartmentsJoinDto;
import com.backoffice.backoffice.dto.departments.DepartmentsUpdateDto;
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
    public void departmentSave(DepartmentsJoinDto departmentsJoinDto) {
        try {
            departmentsMapper.departmentSave(departmentsJoinDto);
        } catch (Exception e) {
            throw new CommonExceptionHandler(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    //부서 업데이트
    @Transactional
    public DepartmentsUpdateDto departmentsUpdate(DepartmentsUpdateDto departmentsUpdateDto) {
            departmentsMapper.departmentsUpdate(departmentsUpdateDto);
        return departmentsUpdateDto;
    }

    //부서 삭제
    @Transactional
    public DepartmentsDeleteDto departmentsDelete(String name) {
        try {
            DepartmentsDeleteDto departmentsDeleteDto = new DepartmentsDeleteDto();
            departmentsDeleteDto.setName(name);
            departmentsMapper.departmentsDelete(departmentsDeleteDto);

            return departmentsDeleteDto;
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
