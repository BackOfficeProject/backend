package com.backoffice.backoffice.mapper;

import com.backoffice.backoffice.dto.GradesDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GradesMapper {

    //직급 생성
    void gradesSave(GradesDto gradesDto);

    //특정 직급 하나 조회
    List<GradesDto> findGrades(String name);

    //직급 목록 조회
    List<GradesDto> findAllGrades();

    //직급 수정
    void gradesUpdate(GradesDto gradesDto);

    //직급 삭제
    void gradesDelete(GradesDto gradesDto);
}
