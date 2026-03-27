package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Submission;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    boolean existsByTokenAndSemesterAndBranchAndSection(
        String token, String semester, String branch, String section
    );
}