package com.example.blogheavenapplication.controllers;

import com.example.blogheavenapplication.entities.Member;
import com.example.blogheavenapplication.entities.Post;
import com.example.blogheavenapplication.services.AddressService;
import com.example.blogheavenapplication.services.MemberService;
import com.example.blogheavenapplication.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/* Klass:
* Innehåller endpoints där användaren behöver vara inloggad med rollen blog_USER eller blog_ADMIN.
 */

@RestController
@RequestMapping("/api")
public class Controller {

    /*
    *** alla oavsett inloggad eller ej
    get posts - hämta alla blogginlägg
    get posts id - hämta ett specifikt blogginlägg med id
    ???¿¿¿  post new user - skapar ny användare

    *** USER & ADMIN
    post new post - skapa ett nytt blogginlägg
    put update post id - uppdaterar ett befintligt blogginläg baserat på id
    delete delete post id - tar bort ett befintligt blogginlägg baserat på id

    ** ADMIN
    get users - hämtar alla användare från databasen
    get users id - hämtar information om specifik användare med id


    ** endast autentiserade användare - skapa, uppdatera och ta bort inlägg
    ** rollbaserad åtkomstkontroll - endast admin kan hämta information om användare

     */


    @Autowired
    private MemberService memberService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PostService postService;


    // ADMIN & USER
    @PostMapping("/newpost")
    @PreAuthorize("hasAnyRole('blog_ADMIN', 'blog_USER')")
    public ResponseEntity<Post> newPost(@RequestBody Post post){
        return new ResponseEntity<>(postService.addNewPost(post), HttpStatus.CREATED);
    }

    @PutMapping("/updatepost/{id}")
    @PreAuthorize("hasAnyRole('blog_ADMIN', 'blog_USER')")
    public ResponseEntity<Post> updatePostById(@PathVariable("id") int id, @RequestBody Post post){
        return new ResponseEntity<>(postService.updatePostById(id, post), HttpStatus.OK);

    }

    @DeleteMapping("/deletepost/{id}")
    @PreAuthorize("hasAnyRole('blog_ADMIN', 'blog_USER')")
    public ResponseEntity<String> deletePostById(@PathVariable ("id") int id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post deleted.", HttpStatus.OK);
    }


    // ADMIN
    @GetMapping("/users")
    @PreAuthorize("hasRole('blog_ADMIN')")
    public ResponseEntity<List<Member>> getAllMembers(){
        return ResponseEntity.ok(memberService.fetchAllMembers());
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasRole('blog_ADMIN')")
    public ResponseEntity<Member> getMemberById(@PathVariable ("id") int id){
        return ResponseEntity.ok(memberService.fetchMemberById(id));
    }


}
