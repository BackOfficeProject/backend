package com.backoffice.backoffice.service;

import com.backoffice.backoffice.dto.DepartmentsDto;
import com.backoffice.backoffice.exception.CommonExceptionHandler;
import com.backoffice.backoffice.exception.ErrorCode;
import com.backoffice.backoffice.mapper.DepartmentsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DepartmentsService {

    private final DepartmentsMapper departmentsMapper;

    @Transactional
    public void departmentSave(DepartmentsDto departmentsDto) {
        try {
            departmentsMapper.departmentSave(departmentsDto);
        } catch (Exception e) {
            throw new CommonExceptionHandler(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
