package net.javaguide.springboot.service;


import net.javaguide.springboot.dto.CommentDto;
import net.javaguide.springboot.entity.Comment;

public interface CommentService {
    void createComment(String postUrl, CommentDto commentDto);
}
