package com.example.blogheavenapplication.controllers;

import com.example.blogheavenapplication.entities.Member;
import com.example.blogheavenapplication.entities.Post;
import com.example.blogheavenapplication.services.MemberService;
import com.example.blogheavenapplication.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Klass: AuthenticatedUserController - Innehåller endpoints för autentiserade användare med rollerna blog_USER och blog_ADMIN.

@RestController
@RequestMapping("/api")
public class AuthenticatedUserController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PostService postService;


    // ADMIN & USER

    // Lägga till nytt blogginlägg
    @PostMapping("/newpost")
    @PreAuthorize("hasAnyRole('blog_ADMIN', 'blog_USER')")
    public ResponseEntity<Post> newPost(@RequestBody Post post){
        return new ResponseEntity<>(postService.addNewPost(post), HttpStatus.CREATED);
    }

    // Uppdatera blogginlägg med id
    @PutMapping("/updatepost/{id}")
    @PreAuthorize("hasAnyRole('blog_ADMIN', 'blog_USER')")
    public ResponseEntity<Post> updatePostById(@PathVariable("id") int id, @RequestBody Post post){
        return new ResponseEntity<>(postService.updatePostById(id, post), HttpStatus.OK);
    }

    // Ta bort blogginlägg med id
    @DeleteMapping("/deletepost/{id}")
    @PreAuthorize("hasAnyRole('blog_ADMIN', 'blog_USER')")
    public ResponseEntity<String> deletePostById(@PathVariable ("id") int id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Blog post deleted.", HttpStatus.OK);
    }


    // ADMIN

    // Hämta alla användare
    @GetMapping("/users")
    @PreAuthorize("hasRole('blog_ADMIN')")
    public ResponseEntity<List<Member>> getAllMembers(){
        return ResponseEntity.ok(memberService.fetchAllMembers());
    }

    // Hämta användare med id
    @GetMapping("/users/{id}")
    @PreAuthorize("hasRole('blog_ADMIN')")
    public ResponseEntity<Member> getMemberById(@PathVariable ("id") int id){
        return ResponseEntity.ok(memberService.fetchMemberById(id));
    }

}
