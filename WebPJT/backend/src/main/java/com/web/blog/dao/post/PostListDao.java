package com.web.blog.dao.post;

import java.util.List;
import java.util.Optional;

import com.web.blog.model.post.PostList;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostListDao extends JpaRepository<PostList, String> {
    Optional<PostList> findByEmail(String email);
    PostList findByPid(int pid);
    List<PostList> findByFlag(int flag);
    List<PostList> findByActivityLike(String word);
    List<PostList> findByPriceLessThan(int word);
}