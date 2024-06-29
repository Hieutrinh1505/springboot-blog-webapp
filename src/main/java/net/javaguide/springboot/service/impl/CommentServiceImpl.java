package net.javaguide.springboot.service.impl;


import net.javaguide.springboot.dto.CommentDto;
import net.javaguide.springboot.entity.Comment;
import net.javaguide.springboot.entity.Post;
import net.javaguide.springboot.mapper.CommentMapper;
import net.javaguide.springboot.repository.CommentRepository;
import net.javaguide.springboot.repository.PostRepository;
import net.javaguide.springboot.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void createComment(String postUrl, CommentDto commentDto) {
        Post post = postRepository.findByUrl(postUrl).get();

        Comment comment = CommentMapper.mapToComment(commentDto);
        comment.setPost(post);
        commentRepository.save(comment);
    }
}
