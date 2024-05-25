package com.example.myrok.repository;

import com.example.myrok.domain.RecordTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordTagRepository extends JpaRepository<RecordTag,Long> {
    List<RecordTag> findAllByRecordIdAndDeletedIsFalse(Long id);
    @Query("SELECT rt.tagName FROM RecordTag rt WHERE rt.record.id=:recordId AND rt.deleted=false")
    List<String> findTagsByRecordIdAndDeletedIsFalse(@Param("recordId")Long recordId);
}
