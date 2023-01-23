package com.laureles.blog.controller;

import com.laureles.blog.payload.PostDto;
import com.laureles.blog.payload.PostDtoV2;
import com.laureles.blog.payload.PostResponse;
import com.laureles.blog.service.PostService;
import com.laureles.blog.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping()
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // create blog post rest api
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/v1/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    // get all posts rest api
    @GetMapping("/api/v1/posts")
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false)int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){

        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    // get post by id rest api
    //versioning technique through URI path
    @GetMapping("/api/v1/posts/{id}")
    public ResponseEntity<PostDto> getPostByIdV1(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    // get post by id rest api
    //versioning technique through query parameters
    @GetMapping(value = "/api/posts/{id}", params = "version=v1")
    public ResponseEntity<PostDto> getPostByIdVerQueryParameters(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    // get post by id rest api
    //versioning technique through custom headers
    @GetMapping(value = "/api/posts/{id}", headers = "X-API-VERSION=v1")
    public ResponseEntity<PostDto> getPostByIdVerCustomHeader(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    // get post by id rest api
    //versioning technique through content negotiation
    @GetMapping(value = "/api/posts/{id}", produces = "application/vnd.laureles.v1+json")
    public ResponseEntity<PostDto> getPostByIdVerContentNegotiation(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }


    // get post by id rest api
    //versioning technique through URI path
    @GetMapping("/api/v2/posts/{id}")
    public ResponseEntity<PostDtoV2> getPostByIdV2(@PathVariable(name = "id") long id){
        PostDto postDto = postService.getPostById(id);
        PostDtoV2 postDtoV2 = new PostDtoV2();
        postDtoV2.setId(postDto.getId());
        postDtoV2.setTitle(postDto.getTitle());
        postDtoV2.setDescription(postDto.getDescription());
        postDtoV2.setContent(postDto.getContent());
        List<String> tags = new ArrayList<>();
        tags.add("Java");
        tags.add("Spring Boot");
        tags.add("AWS");
        postDtoV2.setTags(tags);

        return ResponseEntity.ok(postDtoV2);
    }

    // update post by id rest api
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/api/v1/posts/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") long id){

        PostDto postResponse = postService.updatePost(postDto, id);

        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    // delete post rest api
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/api/v1/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){

        postService.deletePostById(id);

        return new ResponseEntity<>("Post entity deleted successfully", HttpStatus.OK);
    }
//
//    //Build Get post by category REST API
//    @GetMapping("/category/{id}")
//    public ResponseEntity<List<PostDto>> getPostsByCategory(@RequestBody PostDto postDto, @PathVariable("id") long categoryId){
//        List<PostDto> postDtos = postService.getPostsByCategory(postDto, categoryId);
//        return ResponseEntity.ok(postDtos);
//    }

    //Build Get post by category REST API
    @GetMapping("/api/v1/posts/category/{id}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("id") long categoryId){
        List<PostDto> postDtos = postService.getPostsByCategory(categoryId);
        return ResponseEntity.ok(postDtos);
    }
}
