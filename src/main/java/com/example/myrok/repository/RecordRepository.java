package com.example.myrok.repository;

import com.example.myrok.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    @Query("SELECT r FROM Record r WHERE r.project.id = :projectId")
    List<Record> findAllByProjectId(@Param("projectId") Long projectId);
}
