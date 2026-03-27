package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.AcademicDetail;

public interface AcademicRepository extends JpaRepository<AcademicDetail, Long> {

    @Query("SELECT DISTINCT a.semester FROM AcademicDetail a")
    List<String> findSemesters();

    @Query("SELECT DISTINCT a.branch FROM AcademicDetail a")
    List<String> findBranches();

    @Query("SELECT DISTINCT a.section FROM AcademicDetail a")
    List<String> findSections();
}