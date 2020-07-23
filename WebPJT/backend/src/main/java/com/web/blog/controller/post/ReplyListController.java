package com.web.blog.controller.post;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import com.web.blog.dao.post.ReplyListDao;
import com.web.blog.model.post.ReplyList;

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
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/reply")

public class ReplyListController {
    
    @Autowired
    ReplyListDao replyListDao;

    @GetMapping("/list")
    @ApiOperation(value ="댓글 목록")
    public List<ReplyList> selectAll(@RequestBody ReplyList request) throws SQLException, IOException{
        List<ReplyList> list = new LinkedList<>();
        int tempId = request.getPid();
        list = replyListDao.findByPid(tempId);
        // list = replyListDao.findAll();
        return list;
    }

    @PutMapping("/register")
    @ApiOperation(value = "댓글 등록")
    public Object register(@RequestBody ReplyList request) throws SQLException, IOException{
        try {
            ReplyList temp = new ReplyList();
            temp.setRid(request.getRid());
            temp.setPid(request.getPid());
            temp.setRegNickname(request.getRegNickname());
            temp.setRegContent(request.getRegContent());       
            LocalDateTime time = LocalDateTime.now();
            temp.setRegDate(time);
            replyListDao.save(temp);

            return temp;
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("delete/{rid}")
    @ApiOperation(value = "댓글 삭제")
    public Object delete(@PathVariable int rid) throws SQLException, IOException{
        ReplyList reply = replyListDao.findByRid(rid);
        if(reply != null){
            replyListDao.delete(reply);
            return "댓글 삭제";
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


}