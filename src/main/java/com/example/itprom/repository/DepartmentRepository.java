package com.example.itprom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.itprom.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
