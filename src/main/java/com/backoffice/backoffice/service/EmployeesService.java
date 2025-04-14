package com.backoffice.backoffice.service;

import com.backoffice.backoffice.dto.EmployeesDto;
import com.backoffice.backoffice.mapper.EmployeesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeesService {


    private final EmployeesMapper employeesMapper;

    public void save(EmployeesDto employeesDto) {
        try {
            // 이메일을 찾은 후, 반환된 값이 null이면 새로운 이메일로 저장
            String existingMail = employeesMapper.findByEmail(employeesDto.getEmail());

            // 이메일이 존재하지 않으면 저장
            if (existingMail == null) {
                employeesMapper.save(employeesDto);
            } else {
                // 이메일이 존재하면 이미 존재하는 메일로 처리
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
