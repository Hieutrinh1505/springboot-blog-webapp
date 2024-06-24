package net.javaguide.springboot.service.impl;

import net.javaguide.springboot.dto.PostDto;
import net.javaguide.springboot.entity.Post;
import net.javaguide.springboot.mapper.PostMapper;
import net.javaguide.springboot.repository.PostRepository;
import net.javaguide.springboot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<PostDto> findAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostMapper::mapToPostDto).collect(Collectors.toList());
    }
}
