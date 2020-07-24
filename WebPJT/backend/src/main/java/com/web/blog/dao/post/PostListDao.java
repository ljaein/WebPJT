package com.web.blog.dao.post;

import java.util.List;

import com.web.blog.model.post.PostList;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostListDao extends JpaRepository<PostList, String> {
    List<PostList> findByEmail(String email);
    PostList findByPid(int pid);
    List<PostList> findByFlag(int flag);
    List<PostList> findByFlagOrderByCreateDateDesc(int flag);
    List<PostList> findByActivityLikeOrderByCreateDateDesc(String word);
    List<PostList> findByTitleLikeOrderByCreateDateDesc(String word);
    List<PostList> findByPriceLessThanEqualOrderByCreateDateDesc(int word);
    
}