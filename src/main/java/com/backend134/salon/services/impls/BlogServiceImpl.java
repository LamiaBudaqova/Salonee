package com.backend134.salon.services.impls;

import com.backend134.salon.dtos.blog.BlogDto;
import com.backend134.salon.models.Blog;
import com.backend134.salon.repositories.BlogRepository;
import com.backend134.salon.services.BlogService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<BlogDto> getLatestPosts() {
        return blogRepository.findTop2ByOrderByCreatedAtDesc()
                .stream()
                .map(blog -> modelMapper.map(blog, BlogDto.class))
                .toList();
    }

    @Override
    public List<BlogDto> getAllBlogs() {
        return blogRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(blog -> modelMapper.map(blog, BlogDto.class))
                .toList();
    }

    @Override
    public BlogDto getById(Long id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bloq tapılmadı"));
        return modelMapper.map(blog, BlogDto.class);
    }

    @Override
    public void create(BlogDto dto) {
        Blog blog = modelMapper.map(dto, Blog.class);
        blog.setCreatedAt(LocalDate.now()); // əlavə ediləndə bugünkü tarix
        blogRepository.save(blog);
    }

    @Override
    public void update(Long id, BlogDto dto) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bloq tapılmadı"));
        blog.setTitle(dto.getTitle());
        blog.setContent(dto.getContent());
        blog.setImagePath(dto.getImagePath());
        blogRepository.save(blog);
    }

    @Override
    public void delete(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Page<BlogDto> getBlogsPage(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        return blogRepository.findAllByOrderByCreatedAtDesc(pageable)
                .map(blog -> modelMapper.map(blog, BlogDto.class));
    }
}
