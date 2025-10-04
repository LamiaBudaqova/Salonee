package com.backend134.salon.admin.services.impls;

import com.backend134.salon.admin.dtos.AdminBlogCreateDto;
import com.backend134.salon.admin.dtos.AdminBlogResponseDto;
import com.backend134.salon.admin.dtos.AdminBlogUpdateDto;
import com.backend134.salon.models.Blog;
import com.backend134.salon.repositories.BlogRepository;
import com.backend134.salon.admin.services.AdminBlogService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminBlogServiceImpl implements AdminBlogService {
    private final BlogRepository blogRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AdminBlogResponseDto> getAll() {
        return blogRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(blog -> modelMapper.map(blog, AdminBlogResponseDto.class))
                .toList();
    }

    @Override
    public AdminBlogResponseDto getById(Long id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog tapılmadı"));
        return modelMapper.map(blog, AdminBlogResponseDto.class);
    }

    @Override
    public void create(AdminBlogCreateDto dto) {
        Blog blog = modelMapper.map(dto, Blog.class);
        blog.setCreatedAt(LocalDate.now());
        blogRepository.save(blog);
    }

    @Override
    public void update(Long id, AdminBlogUpdateDto dto) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog tapılmadı"));

        blog.setTitle(dto.getTitle());
        blog.setContent(dto.getContent());
        blog.setImagePath(dto.getImagePath());

        blogRepository.save(blog);
    }

    @Override
    public void delete(Long id) {
        blogRepository.deleteById(id);
    }
}

