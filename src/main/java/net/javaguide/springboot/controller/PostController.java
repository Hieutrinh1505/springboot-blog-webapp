package net.javaguide.springboot.controller;

import jakarta.validation.Valid;
import net.javaguide.springboot.dto.CommentDto;
import net.javaguide.springboot.dto.PostDto;
import net.javaguide.springboot.entity.Comment;
import net.javaguide.springboot.entity.Post;
import net.javaguide.springboot.service.CommentService;
import net.javaguide.springboot.service.PostService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.naming.Binding;
import java.util.List;

@Controller
public class PostController {

    private PostService postService;
    private CommentService commentService;

    public PostController(PostService postService,CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    // create handler method, GET request and return model and view
    @GetMapping("/admin/posts")
    public String posts(Model model) {
        List<PostDto> posts = postService.findAllPosts();
        model.addAttribute("posts",posts);

        return "admin/posts";
    }

    //handler method to handle new post request
    @GetMapping("/admin/posts/newpost")
    public String newPostForm(Model model)
    {
        PostDto postDto = new PostDto();
        model.addAttribute("post",postDto);
        return "admin/create_post";


    }

    // handler method to handle form submit request
    @PostMapping("/admin/posts")
    public String createPost(@Valid @ModelAttribute("post") PostDto postDto, BindingResult result, Model model)
    {
        if(result.hasErrors()){
            model.addAttribute("post",postDto);
            return "/admin/create_post";
        }
        postDto.setUrl(getUrl(postDto.getTitle()));
        postService.createPost(postDto);
        return "redirect:/admin/posts";
    }

    private static String getUrl(String postTitle)
    {
        String title = postTitle.trim().toLowerCase();
        String url = title.replaceAll("\\s+","-");
        url = url.replaceAll("[^A-Za-z0-9]","-");
        return url;
    }

    //handler method to handle edit post request
    @GetMapping("/admin/posts/{postId}/edit")
    public String editPostForm(@PathVariable("postId") Long postId,Model model){
        PostDto postDto = postService.findPostById(postId);
        model.addAttribute("post",postDto);
        return "admin/edit_post";
    }

    //handler method to handle edit post form submit request
    @PostMapping("/admin/posts/{postId}")
    public String updatePost(@PathVariable("postId") Long postId,@Valid @ModelAttribute("post") PostDto post,BindingResult result,Model model) {
        PostDto postDto = postService.findPostById(postId);
        if(result.hasErrors()){
            post.setId(postId);
            model.addAttribute("post",post);
            return "admin/edit_post";
        }

        post.setId(postId);
        postService.updatePost(post);
        return "redirect:/admin/posts";
    }

    // handler method to handle delete post
    @GetMapping("/admin/posts/{postId}/delete")
    public String deletePost(@PathVariable("postId") Long postId) {
        postService.deletePost(postId);
        return "redirect:/admin/posts";
    }

    // handler method to view post
    @GetMapping("/admin/posts/{postUrl}/view")
    public String viewPost(@PathVariable("postUrl") String postUrl,Model model){

        PostDto postDto = postService.findPostByUrl(postUrl);
        model.addAttribute("post",postDto);
        return "admin/view_post";
    }

    //handler method to list comments request
    @GetMapping("/admin/posts/comments")
    public String postComments(Model model)
    {
        List<CommentDto> comments = commentService.findAllComments();
        model.addAttribute("comments",comments);
        return "admin/comments";
    }

    //handler method to delete comment
    @GetMapping("admin/posts/comments/{commentId}/delete")
    public  String deleteComment(@PathVariable("commentId") Long commentId,Model model)
    {
        commentService.deleteComment(commentId);

        List<CommentDto> comments = commentService.findAllComments();

        model.addAttribute("comments",comments);
        return "redirect:/admin/posts/comments";
    }
}
