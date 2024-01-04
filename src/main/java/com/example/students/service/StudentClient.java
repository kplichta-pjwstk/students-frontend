package com.example.students.service;

import com.example.students.frontend.CreateStudent;
import com.example.students.frontend.StudentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "studentClient", url = "http://localhost:8080/students")
public interface StudentClient {

    @GetMapping("/{id}")
    StudentDto getStudentById(@PathVariable UUID id);

    @PostMapping
    void createStudent(@RequestBody @Validated CreateStudent student);
}
