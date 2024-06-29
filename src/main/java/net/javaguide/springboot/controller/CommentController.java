package net.javaguide.springboot.controller;

import jakarta.validation.Valid;
import net.javaguide.springboot.dto.CommentDto;
import net.javaguide.springboot.dto.PostDto;
import net.javaguide.springboot.service.CommentService;
import net.javaguide.springboot.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;

    public CommentController(CommentService commentService,PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    // handler method to create form submit request
    @PostMapping("/{postUrl}/comments")
    public String createComment(@PathVariable("postUrl") String postUrl, @Valid @ModelAttribute("comment") CommentDto commentDto
                        ,BindingResult result, Model model) {
        if(result.hasErrors())
        {
            model.addAttribute("comment",commentDto);
            model.addAttribute("post",postService.findPostByUrl(postUrl));
            return "blog/blog_post";
        }
        commentService.createComment(postUrl, commentDto);
//        PostDto postDto = postService.findPostByUrl(postUrl);
//        model.addAttribute("post",postDto);
        return "redirect:/post/" + postUrl;
    }
}
