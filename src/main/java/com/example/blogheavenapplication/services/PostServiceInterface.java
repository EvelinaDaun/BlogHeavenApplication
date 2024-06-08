package com.example.blogheavenapplication.services;

import com.example.blogheavenapplication.entities.Post;

import java.util.List;

// PostServiceInterface - Strukturen för PostService

public interface PostServiceInterface {

    List<Post> fetchAllPosts();
    Post fetchPostById(int id);
    Post addNewPost(Post post);
    Post updatePostById(int id, Post post);
    void deletePostById(int id);


}
