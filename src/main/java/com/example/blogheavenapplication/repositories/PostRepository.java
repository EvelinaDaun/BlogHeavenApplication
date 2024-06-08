package com.example.blogheavenapplication.repositories;

import com.example.blogheavenapplication.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// PostRepository - Handtaget till Post tabellen
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
