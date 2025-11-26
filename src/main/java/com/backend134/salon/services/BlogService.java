package com.backend134.salon.services;

import com.backend134.salon.dtos.blog.BlogDto;
import com.backend134.salon.models.Blog;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BlogService {
    List<BlogDto> getLatestPosts();   // home üçün
    List<BlogDto> getAllBlogs();      // bütün bloglar
    BlogDto getById(Long id);         // detail
    void create(BlogDto dto);         // admin üçün əlavə etdik
    void update(Long id, BlogDto dto);// admin üçün
    void delete(Long id);
    Page<BlogDto> getBlogsPage(int page, int size);

}
