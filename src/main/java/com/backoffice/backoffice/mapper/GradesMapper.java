package com.backoffice.backoffice.mapper;

import com.backoffice.backoffice.dto.grades.GradesDeleteDto;
import com.backoffice.backoffice.dto.grades.GradesDto;
import com.backoffice.backoffice.dto.grades.GradesJoinDto;
import com.backoffice.backoffice.dto.grades.GradesUpdateDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GradesMapper {

    //직급 생성
    void gradesSave(GradesJoinDto gradesJoinDto);

    //특정 직급 하나 조회
    List<GradesDto> findGrades(String name);

    //직급 목록 조회
    List<GradesDto> findAllGrades();

    //직급 수정
    void gradesUpdate(GradesUpdateDto gradesUpdateDto);

    //직급 삭제
    void gradesDelete(GradesDeleteDto gradesDeleteDto);

    GradesDto findId(Integer id);


}
