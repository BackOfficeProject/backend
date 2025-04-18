package com.backoffice.backoffice.service;

import com.backoffice.backoffice.dto.GradesDto;
import com.backoffice.backoffice.mapper.GradesMapper;
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
    public void gradesSave(GradesDto gradesDto) {
        try {
            gradesMapper.gradesSave(gradesDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //직급 수정
    @Transactional
    public void gradesUpdate(GradesDto gradesDto) {
        try {
gradesMapper.gradesUpdate(gradesDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //직급 삭제
    @Transactional
    public void gradesDelete(GradesDto gradesDto) {
        try {
            gradesMapper.gradesDelete(gradesDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
}
