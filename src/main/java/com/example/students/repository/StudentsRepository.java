package com.example.students.repository;

import com.example.students.entity.StudentsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentsRepository extends JpaRepository<StudentsEntity, String> {
  //  Page<StudentsEntity> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<StudentsEntity> findByNameContainingIgnoreCase(String filter, Pageable pageable);
}
