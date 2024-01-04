package com.example.students.frontend;

import java.util.UUID;

public class StudentDto {

    private UUID id;
    private String name;
    private StudentUnit unit;
    private Long index;
    public StudentDto() {
    }

    public StudentDto(UUID id, String name, StudentUnit unit, Long index) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.index = index;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StudentUnit getUnit() {
        return unit;
    }

    public void setUnit(StudentUnit unit) {
        this.unit = unit;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }
}
