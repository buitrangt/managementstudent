package com.example.students;

import com.example.students.dto.StudentsRequest;
import com.example.students.dto.StudentsResponse;
import com.example.students.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentsController {

    private final StudentsService studentsService;

    @Autowired
    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentsResponse createStudent(@RequestBody StudentsRequest request) {
        return studentsService.create(request);
    }

    @GetMapping
    public List<StudentsResponse> getAllStudents() {
        return studentsService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentsResponse getStudentById(@PathVariable String id) {
        return studentsService.getStudentsById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentsResponse updateStudent(@PathVariable String id, @RequestBody StudentsRequest request) {
        return studentsService.updateStudents(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable String id) {
        studentsService.deleteStudents(id);
    }

    @GetMapping("/paged")
    public Page<StudentsResponse> getAllStudentsPaged(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "5") int size,
                                                      @RequestParam(required = false) String filter) {
        Pageable pageable = PageRequest.of(page, size);
        return studentsService.findAllStudents(pageable, filter);
    }
}
