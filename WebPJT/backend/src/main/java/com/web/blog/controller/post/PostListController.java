﻿package com.web.blog.controller.post;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.web.blog.dao.post.PostListDao;
import com.web.blog.model.post.PostList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/post")
public class PostListController {
    //test
    @Autowired
    PostListDao postDao;


    @GetMapping("/search/{key}/{word}")
    @ApiOperation(value = "검색")
    public List<PostList> search(@PathVariable String key, @PathVariable String word, @RequestParam int page) throws SQLException, IOException {
        List<PostList> post = new LinkedList<>();
        if(key.equals("")){
            post = postDao.findByFlagOrderByCreateDateDesc(1);
        }else if(key.equals("title")){
            post = postDao.findByTitleLikeOrderByCreateDateDesc("%"+word+"%");
        }else if(key.equals("activity")){
            post = postDao.findByActivityLikeOrderByCreateDateDesc("%"+word+"%");
        }else if(key.equals("price")){
            int price = Integer.parseInt(word);
            post = postDao.findByPriceLessThanEqualOrderByCreateDateDesc(price);
        }

        int start = page * 9;
        int end = start + 9;

        if(end > post.size()) {
            end = post.size();
        }

        List<PostList> list = new LinkedList<>();
        for (int i = start; i < end; i++) {
            list.add(post.get(i));
        }

        return list;
    }

    @GetMapping("/list")
    @ApiOperation(value = "포스트 리스트")
    public List<PostList> selectAll() throws SQLException, IOException {
        List<PostList> temp = new LinkedList<>();
        temp = postDao.findByFlagOrderByCreateDateDesc(1);
        return temp;
    }

    @GetMapping("/listbylike")
    @ApiOperation(value="포스트 리스트 좋아요 정렬")
    public List<PostList> selectAllByLike() throws SQLException, IOException {
        List<PostList> temp = new LinkedList<>();
        temp = postDao.findAllByOrderByLikecntDesc();
        return temp;
    }

    // infinite-loading paging
    @GetMapping("/getList")
    @ResponseBody
    public List<PostList> getList(@RequestParam int page) {
        int start = page * 9;
        int end = start + 9;

        List<PostList> temp = new LinkedList<>();
        temp = postDao.findByFlagOrderByCreateDateDesc(1);

        if(end > temp.size()) {
            end = temp.size();
        }

        List<PostList> list = new LinkedList<>();
        for (int i = start; i < end; i++) {
            list.add(temp.get(i));
        }

        return list;
    }

    @GetMapping("/detail/{pid}")
    @ApiOperation(value = "포스트 상세정보")
    public Object selectDetail(@PathVariable int pid) throws SQLException, IOException {
        PostList post = postDao.findByPid(pid);
        if(post!=null){
            // System.out.println(post);
            return post;
        }else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    

    @PutMapping("/modify")
    @ApiOperation(value = "포스트 수정하기")
    public Object modify(@Valid @RequestBody PostList request) throws SQLException, IOException {
        try {
            PostList post = postDao.findByPid(request.getPid());
            if(post!=null){

                PostList newTemp = post;
                newTemp.setTitle(request.getTitle());
                newTemp.setLocation(request.getLocation());
                newTemp.setImgurl(request.getImgurl());
                newTemp.setPrice(request.getPrice());
                newTemp.setSdate(request.getSdate());
                newTemp.setEdate(request.getEdate());
                newTemp.setCompanyInfo(request.getCompanyInfo());
                newTemp.setDetail(request.getDetail());
                newTemp.setActivity(request.getActivity());
                LocalDateTime time = LocalDateTime.now();
                newTemp.setCreateDate(time);
                // System.out.println(newTemp);
                postDao.save(newTemp);
                return newTemp;
            } else {
                System.out.println("DB에 없음.");
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{pid}")
    @ApiOperation(value = "포스트 삭제")
    public Object delete(@PathVariable int pid) throws SQLException, IOException {
        PostList post = postDao.findByPid(pid);
        if(post!=null){
            postDao.delete(post);
            return "포스트 삭제 완료";
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/regist")
    @ApiOperation("포스트 등록")
    public Object regist(@RequestBody PostList request) throws SQLException, IOException {
        try {
            PostList temp = new PostList();
            temp.setEmail(request.getEmail());
            temp.setTitle(request.getTitle());
            temp.setLocation(request.getLocation());
            temp.setImgurl(request.getImgurl());
            temp.setPrice(request.getPrice());
            temp.setSdate(request.getSdate());
            temp.setEdate(request.getEdate());
            temp.setCompanyInfo(request.getCompanyInfo());
            temp.setDetail(request.getDetail());
            temp.setFlag(1);
            temp.setActivity(request.getActivity());
            LocalDateTime time = LocalDateTime.now();
            temp.setCreateDate(time);
            postDao.save(temp);

            return temp;    
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}