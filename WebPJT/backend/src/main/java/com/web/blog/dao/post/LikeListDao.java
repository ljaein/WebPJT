package com.web.blog.dao.post;

import com.web.blog.model.post.LikeList;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeListDao extends JpaRepository<LikeList, String> {
    public LikeList findByNo(int no);
}