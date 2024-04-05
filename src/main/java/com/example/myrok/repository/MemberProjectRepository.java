package com.example.myrok.repository;

import com.example.myrok.domain.Member;
import com.example.myrok.domain.MemberProject;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberProjectRepository extends JpaRepository<MemberProject, Long> {
    Optional<MemberProject> findByMember(Member member);
    Optional<MemberProject> findByMemberIdAndProjectId(Long memberId, @Nullable Long projectId);
}
