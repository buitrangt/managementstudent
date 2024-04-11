package com.example.students.service;

import com.example.students.dto.StudentsRequest;
import com.example.students.dto.StudentsResponse;
import com.example.students.entity.StudentsEntity;
import com.example.students.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentsServiceImpl implements StudentsService {
    private final StudentsRepository studentsRepository;

    @Autowired
    public StudentsServiceImpl(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    @Override
    @Transactional
    public StudentsResponse create(StudentsRequest request) {
        StudentsEntity entity = StudentsMapping.convertDtotoEntity(request);
        StudentsEntity savedEntity = studentsRepository.save(entity);
        return StudentsMapping.convertEntityToStudentsResponse(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentsResponse> getAllStudents() {
        List<StudentsEntity> entities = studentsRepository.findAll();
        return entities.stream().map(StudentsMapping::convertEntityToStudentsResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public StudentsResponse getStudentsById(String id) {
        Optional<StudentsEntity> entity = studentsRepository.findById(id);
        if (entity.isPresent()) {
            return StudentsMapping.convertEntityToStudentsResponse(entity.get());
        } else {
            throw new RuntimeException("Student not found with id: " + id); // Hoặc xử lý ngoại lệ tùy chỉnh của bạn
        }
    }

    @Override
    @Transactional
    public StudentsResponse updateStudents(String id, StudentsRequest request) {
        Optional<StudentsEntity> existingEntity = studentsRepository.findById(id);
        if (existingEntity.isPresent()) {
            StudentsEntity updatedEntity = existingEntity.get();
            updatedEntity.setName(request.getName());
            updatedEntity.setPassword(request.getPassword());
            updatedEntity.setLastname(request.getLastname());
            StudentsEntity savedEntity = studentsRepository.save(updatedEntity);
            return StudentsMapping.convertEntityToStudentsResponse(savedEntity);
        } else {
            throw new RuntimeException("Student not found with id: " + id); // Hoặc xử lý ngoại lệ tùy chỉnh của bạn
        }
    }

    @Override
    @Transactional
    public void deleteStudents(String id) {
        studentsRepository.deleteById(id);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<StudentsResponse> findAllStudents(Pageable pageable, String filter) {
        Page<StudentsEntity> studentsPage;
        if (filter != null && !filter.isEmpty()) {
            studentsPage = studentsRepository.findByNameContainingIgnoreCase(filter, pageable);
        } else {
            studentsPage = studentsRepository.findAll(pageable);
        }
        return studentsPage.map(StudentsMapping::convertEntityToStudentsResponse);
    }

}
