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

    @Override
    public void createPost(PostDto postDto) {
        Post post = PostMapper.mapToPost(postDto);
        postRepository.save(post);
    }

    @Override
    public PostDto findPostById(Long postId) {
        Post post = postRepository.findById(postId).get();
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public void updatePost(PostDto postDto) {
        Post post = PostMapper.mapToPost(postDto);
        postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public PostDto findPostByUrl(String postUrl) {
        Post post = postRepository.findByUrl(postUrl).get();
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public List<PostDto> searchPosts(String query) {
        List<Post> posts = postRepository.searchPosts(query);
        return posts.stream().map(PostMapper::mapToPostDto).collect(Collectors.toList());
    }
}
