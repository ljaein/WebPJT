package com.web.blog.controller.post;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/temp")
public class TempListController {

    @Autowired
    PostListDao postDao;

    @GetMapping("/list")
    @ApiOperation(value = "임시저장 리스트")
    public List<PostList> selectAll() throws SQLException, IOException {
        List<PostList> temp = new LinkedList<>();
        temp = postDao.findByFlag(0);
        System.out.println(temp);
        return temp;
    }

    @GetMapping("/detail/{pid}")
    @ApiOperation(value = "임시저장 상세정보")
    public Object selectDetail(@PathVariable int pid) throws SQLException, IOException {
        PostList post = postDao.findByPid(pid);
        if(post!=null){
            System.out.println(post);
            return post;
        }else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/tempRegist")
    @ApiOperation("임시저장 등록")
    public Object tespRegist(@RequestBody PostList request) throws SQLException, IOException {
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
            temp.setFlag(0);
            temp.setActivity(request.getActivity());
            LocalDateTime time = LocalDateTime.now();
            temp.setCreateDate(time);
            postDao.save(temp);

            return temp;    
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/modify")
    @ApiOperation(value = "임시저장 수정하기")
    public Object modify(@Valid @RequestBody PostList request) throws SQLException, IOException {
        try {
           PostList temp = postDao.findByPid(request.getPid());
            if(temp!=null){
                PostList newTemp = temp;
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
    @ApiOperation(value = "임시저장 삭제")
    public Object delete(@PathVariable int pid) throws SQLException, IOException {
        PostList post = postDao.findByPid(pid);
        if(post != null){
            postDao.delete(post);
            return "삭제 완료";
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}