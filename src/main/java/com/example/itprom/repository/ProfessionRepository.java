package com.example.itprom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.itprom.model.Profession;

@Repository
public interface ProfessionRepository extends JpaRepository<Profession, Long> {
}