package com.laureles.blog.service;


import com.laureles.blog.payload.PostDto;
import com.laureles.blog.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePostById(long id);

//    List<PostDto> getPostsByCategory(PostDto postDto, long categoryId);

    List<PostDto> getPostsByCategory(long categoryId);

}
