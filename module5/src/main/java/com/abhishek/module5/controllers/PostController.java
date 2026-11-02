package com.abhishek.module5.controllers;

import com.abhishek.module5.dto.PostDTO;
import com.abhishek.module5.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    // Secured annotation is used to secure the method with specific roles or authorities. In this case, only users with ROLE_USER or ROLE_ADMIN can access this method.
    // ROLE_USER and ROLE_ADMIN are the authorities
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public List<PostDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN' OR hasAuthority('POST_VIEW'))")

    // Calling method from a bean
    @PreAuthorize("@postSecurity.isOwnerOfPost(#postId)")
    public PostDTO getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @PostMapping
    public PostDTO createNewPost(@RequestBody PostDTO inputPost) {
        return postService.createNewPost(inputPost);
    }

}
