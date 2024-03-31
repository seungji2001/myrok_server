package com.example.myrok.repository;

import com.example.myrok.domain.MemberRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRecordRepository extends JpaRepository<MemberRecord,Long> {
    List<MemberRecord> findAllByRecordId(Long id);
}
