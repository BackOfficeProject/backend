package com.backoffice.backoffice.service;

import com.backoffice.backoffice.dto.employees.EmployeesDto;
import com.backoffice.backoffice.dto.employees.requestDto.EmployeesDeleteRequest;
import com.backoffice.backoffice.dto.resigns.ResignsDto;
import com.backoffice.backoffice.dto.resigns.requestDto.ResignsQuitRequest;
import com.backoffice.backoffice.dto.resigns.responseDto.ResignsQuitResponse;
import com.backoffice.backoffice.mapper.EmployeesMapper;
import com.backoffice.backoffice.mapper.ResignsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ResignsService {

    private final ResignsMapper resignsMapper;
    private final EmployeesMapper employeesMapper;


    //퇴사
    @Transactional
    public ResignsQuitResponse quit(ResignsQuitRequest resignsQuitRequest) {
        Map<String, Object> params = new HashMap<>();
        params.put("employeeId", resignsQuitRequest.getEmployeeId());
        params.put("resignationReason", resignsQuitRequest.getResignationReason());
//        params.put("isRehired", resignsQuitRequest.isRehired());


        resignsMapper.quit(params);

      employeesMapper.statusChange(resignsQuitRequest.getEmployeeId());

        EmployeesDto employee = employeesMapper.findEmpl(resignsQuitRequest.getEmployeeId());

        boolean checkRehired = resignsMapper.checkRehired(resignsQuitRequest.getEmployeeId());

        Integer id = (Integer) params.get("id");
        return ResignsQuitResponse.builder()
                .id(id)
                .employeeId(employee.getName())
                .resignationReason(resignsQuitRequest.getResignationReason())
                .isRehired(checkRehired ? "Y" : "N")
                .status(employee.isStatus() ? "재직 중" : "퇴사")
                .build();
    }

}
