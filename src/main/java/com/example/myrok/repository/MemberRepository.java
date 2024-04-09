package com.example.myrok.repository;

import com.example.myrok.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.myrok.type.ELoginProvider;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findBySocialIdAndProvider(String socialId, ELoginProvider provider);
}
