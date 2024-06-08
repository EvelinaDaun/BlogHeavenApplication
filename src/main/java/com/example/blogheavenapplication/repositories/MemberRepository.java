package com.example.blogheavenapplication.repositories;

import com.example.blogheavenapplication.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// MemberRepository - Handtaget till member tabellen
@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
}
