package com.backoffice.backoffice.mapper;

import com.backoffice.backoffice.dto.grades.requestDto.GradesDeleteRequest;
import com.backoffice.backoffice.dto.grades.GradesDto;
import com.backoffice.backoffice.dto.grades.requestDto.GradesRegisterRequest;
import com.backoffice.backoffice.dto.grades.requestDto.GradesUpdateRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GradesMapper {

    //직급 생성
    void gradesSave(GradesRegisterRequest gradesRegisterRequest);

    //특정 직급 하나 조회
    List<GradesDto> findGrades(String name);

    //직급 목록 조회
    List<GradesDto> findAllGrades();

    //직급 수정
    void gradesUpdate(GradesUpdateRequest gradesUpdateRequest);

    //직급 삭제
    void gradesDelete(GradesDeleteRequest gradesDeleteRequest);

    GradesDto findId(Integer id);


}
