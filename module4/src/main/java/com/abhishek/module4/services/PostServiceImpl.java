package com.abhishek.module4.services;

import com.abhishek.module4.dto.PostDTO;
import com.abhishek.module4.entities.PostEntity;
import com.abhishek.module4.exceptions.ResourceNotFoundException;
import com.abhishek.module4.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository
                .findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO createNewPost(PostDTO inputPost) {
        PostEntity postEntity = modelMapper.map(inputPost, PostEntity.class);
        postRepository.save(postEntity);
        return modelMapper.map(postEntity, PostDTO.class);
    }

    @Override
    public PostDTO getPostById(Long postId) {
        PostEntity postEntity = getPost(postId);

        return modelMapper.map(postEntity, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO inputPost, Long postId) {
        PostEntity olderPost = getPost(postId);
        inputPost.setId(postId);
        modelMapper.map(inputPost, olderPost);
        PostEntity savedPostEntity = postRepository.save(olderPost);
        return modelMapper.map(savedPostEntity, PostDTO.class);
    }

    private PostEntity getPost(Long postId) {
        return postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + postId + " not found"));
    }
}
