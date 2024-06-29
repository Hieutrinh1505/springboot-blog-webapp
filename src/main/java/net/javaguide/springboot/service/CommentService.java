package net.javaguide.springboot.service;


import net.javaguide.springboot.dto.CommentDto;
import net.javaguide.springboot.entity.Comment;

import java.util.List;

public interface CommentService {
    void createComment(String postUrl, CommentDto commentDto);
    List<CommentDto> findAllComments();
    void deleteComment(Long commentId);
}
