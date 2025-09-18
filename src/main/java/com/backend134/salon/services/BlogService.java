package com.backend134.salon.services;

import com.backend134.salon.models.Blog;

import java.util.List;

public interface BlogService {
    List<Blog> getLatestPosts();
    Blog getById(Long id);
}
