package com.abhishek.module4.services;

import com.abhishek.module4.dto.PostDTO;
import java.util.List;

public interface PostService {
    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO inputPost);

    PostDTO getPostById(Long postId);
}
