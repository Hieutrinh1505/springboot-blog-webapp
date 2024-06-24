package net.javaguide.springboot.service;

import net.javaguide.springboot.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findAllPosts();
}
