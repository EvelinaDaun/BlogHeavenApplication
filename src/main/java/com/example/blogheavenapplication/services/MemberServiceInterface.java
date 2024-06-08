package com.example.blogheavenapplication.services;

import com.example.blogheavenapplication.entities.Member;

import java.util.List;

// MemberServiceInterface - Strukturen för MemberService

public interface MemberServiceInterface {

    List<Member> fetchAllMembers();
    Member fetchMemberById(int id);
    Member addNewMember(Member member);

}
