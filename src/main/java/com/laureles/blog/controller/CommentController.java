package com.laureles.blog.controller;

import com.laureles.blog.payload.CommentDto;
import com.laureles.blog.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(name = "postId") long postId, @Valid @RequestBody CommentDto commentDto){

        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(name = "postId") Long postId){

        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(name = "postId") Long postId, @PathVariable(name = "id") Long commentId){

        CommentDto commentDto = commentService.getCommentById(postId, commentId);

        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(name = "postId") Long postId, @PathVariable(name = "id") Long commentId, @Valid @RequestBody CommentDto commentDto){

        CommentDto updatedComment = commentService.updateComment(postId, commentId, commentDto);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable(name = "postId") long postId, @PathVariable(name = "id") long commentId){

        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}
