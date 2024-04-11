package com.example.students.service;

import com.example.students.dto.StudentsRequest;
import com.example.students.dto.StudentsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentsService {
    StudentsResponse create(StudentsRequest request);
    List<StudentsResponse> getAllStudents();
    StudentsResponse getStudentsById(String id);
    StudentsResponse updateStudents(String id, StudentsRequest request);
    void deleteStudents(String id);
    Page<StudentsResponse> findAllStudents(Pageable pageable, String filter);
}
