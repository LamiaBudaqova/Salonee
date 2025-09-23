package com.backend134.salon.repositories;

import com.backend134.salon.models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findTop2ByOrderByCreatedAtDesc(); // en son 3 post
    List<Blog> findAllByOrderByCreatedAtDesc();
}
