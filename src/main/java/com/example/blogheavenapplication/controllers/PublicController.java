package com.example.blogheavenapplication.controllers;

import com.example.blogheavenapplication.entities.Member;
import com.example.blogheavenapplication.entities.Post;
import com.example.blogheavenapplication.services.MemberService;
import com.example.blogheavenapplication.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Klass: PublicController - Hantera endpoints som alla användare kan nå.

@RestController
@RequestMapping("/api")
public class PublicController {

    @Autowired
    private PostService postService;

    @Autowired
    private MemberService memberService;


    // Alla blogginlägg
    @GetMapping("/posts")
    public List<Post> getAllPosts(){
        return postService.fetchAllPosts();
    }

    // Blogginlägg med id
    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") int id){
        return ResponseEntity.ok(postService.fetchPostById(id));
    }

    // Ny användare
    @PostMapping("/newuser")
    public ResponseEntity<Member> addNewMember(@RequestBody Member member){
        return new ResponseEntity<>(memberService.addNewMember(member), HttpStatus.CREATED);
    }
}
