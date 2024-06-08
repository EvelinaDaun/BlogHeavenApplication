package com.example.blogheavenapplication.services;

import com.example.blogheavenapplication.entities.Post;
import com.example.blogheavenapplication.exceptions.ResourceNotFoundException;
import com.example.blogheavenapplication.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Klass: PostService - Logiken för hanteringen av blogginlägg.
@Service
public class PostService implements PostServiceInterface{

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> fetchAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post fetchPostById(int id) {
        if(postRepository.existsById(id)){
            Optional<Post> existingPost = postRepository.findById(id);
            if(existingPost.isPresent()){
                return existingPost.get();
            }
        }
        throw new ResourceNotFoundException("Post", "id", id);
    }

    @Override
    public Post addNewPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post updatePostById(int id, Post post) {
        postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return postRepository.save(post);
    }

    @Override
    public void deletePostById(int id) {
        postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.deleteById(id);
    }
}
