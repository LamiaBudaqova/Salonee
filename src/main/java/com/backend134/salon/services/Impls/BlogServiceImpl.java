package com.backend134.salon.services.Impls;

import com.backend134.salon.models.Blog;
import com.backend134.salon.repositories.BlogRepository;
import com.backend134.salon.services.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    @Override
    public List<Blog> getLatestPosts() {
        return blogRepository.findTop2ByOrderByCreatedAtDesc();
    }

    @Override
    public Blog getById(Long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bloq tapılmadı"));
    }
}
