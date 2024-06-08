package com.example.blogheavenapplication.services;

import com.example.blogheavenapplication.entities.Member;
import com.example.blogheavenapplication.exceptions.ResourceNotFoundException;
import com.example.blogheavenapplication.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Klass: MemberService - Logiken för att hantera medlemmarna/andvändarna
@Service
public class MemberService implements MemberServiceInterface {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AddressService addressService;

    @Override
    public List<Member> fetchAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member fetchMemberById(int id) {
        if(memberRepository.existsById(id)){
            Optional<Member> existingMember = memberRepository.findById(id);
            if(existingMember.isPresent()){
                return existingMember.get();
            }
        }
        throw new ResourceNotFoundException("Member", "id", id);
    }

    @Override
    public Member addNewMember(Member member) {
        addressService.addAddress(member.getAddress());
        return memberRepository.save(member);
    }
}
