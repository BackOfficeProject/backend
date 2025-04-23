package com.backoffice.backoffice.service;

import com.backoffice.backoffice.dto.grades.*;
import com.backoffice.backoffice.dto.grades.requestDto.GradesDeleteRequest;
import com.backoffice.backoffice.dto.grades.requestDto.GradesRegisterRequest;
import com.backoffice.backoffice.dto.grades.requestDto.GradesUpdateRequest;
import com.backoffice.backoffice.dto.grades.responseDto.GradesDeleteResponse;
import com.backoffice.backoffice.mapper.GradesMapper;
import com.backoffice.backoffice.mapper.dtoMapper.GradesDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradesService {

    private final GradesMapper gradesMapper;


    //직급 생성
    @Transactional
    public void gradesSave(GradesRegisterRequest gradesRegisterRequest) {
        try {
            gradesMapper.gradesSave(gradesRegisterRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //직급 수정
    @Transactional
    public void gradesUpdate(GradesUpdateRequest gradesUpdateRequest) {
        try {
gradesMapper.gradesUpdate(gradesUpdateRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //직급 삭제
    @Transactional
    public GradesDeleteResponse gradesDelete(GradesDeleteRequest gradesDeleteRequest) {

            GradesDto dto = gradesMapper.findId(gradesDeleteRequest.getId());

            gradesMapper.gradesDelete(gradesDeleteRequest);

        return GradesDtoMapper.toResponseDto(dto);
    }
    //특정 직급 하나 조회
    @Transactional
    public List<GradesDto> findGrades(String name) {
        return gradesMapper.findGrades(name);
    }
    //직급 목록 조회
    @Transactional
    public List<GradesDto> findAllGrades() {
        return gradesMapper.findAllGrades();
    }

    @Transactional
    public GradesDto findId(Integer id) {
        return gradesMapper.findId(id);
    }
}
