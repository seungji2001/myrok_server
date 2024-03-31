package com.example.myrok.repository;

import com.example.myrok.domain.RecordTag;
import com.example.myrok.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordTagRepository extends JpaRepository<RecordTag,Long> {
}
