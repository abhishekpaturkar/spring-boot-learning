package com.abhishek.module4.controllers;

import com.abhishek.module4.dto.PostDTO;
import com.abhishek.module4.services.PostService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostDTO> getPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public PostDTO getPostById(@PathVariable("postId") Long postId) {
        return postService.getPostById(postId);
    }

    @PostMapping
    public PostDTO savePosts(@RequestBody PostDTO inputPost) {
        return postService.createNewPost(inputPost);
    }
}
