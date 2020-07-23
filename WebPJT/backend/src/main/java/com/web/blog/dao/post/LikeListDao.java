package com.web.blog.dao.post;

import com.web.blog.model.post.LikeList;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeListDao extends JpaRepository<LikeList, String> {
    public LikeList findByNo(int no);
    public List<LikeList> findByEmail(String email);
    public LikeList findByEmailAndPid(String email, int pid);
}