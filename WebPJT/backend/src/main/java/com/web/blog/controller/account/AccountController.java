package com.web.blog.controller.account;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import com.web.blog.dao.user.UserDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.user.SignupRequest;
import com.web.blog.model.user.User;

import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 400, message = "Bad Request", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    UserDao userDao;

    @GetMapping("/login/{email}/{password}")
    @ApiOperation(value = "로그인")
    public Object login(@PathVariable String email, @PathVariable String password) throws SQLException, IOException {
        try {
            Optional<User> userOpt = userDao.findUserByEmailAndPassword(email, password);
            ResponseEntity response = null;
            if (userOpt.isPresent()) {
                return userOpt;
                // final BasicResponse result = new BasicResponse();
                // result.status = true;
                // result.data = email;
                // response = new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
                return response;
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;

    @PostMapping("/signup")
    @ApiOperation(value = "가입하기")
    public Object signup(@Valid @RequestBody SignupRequest request)
            throws MessagingException, SQLException, IOException {

        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(request.getPassword());
        user.setNickname(request.getNickname());
        userDao.save(user);

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // 제목
            helper.setSubject("회원가입 완료");

            // 수신자
            helper.setTo(request.getEmail());

            // 전달 데이터
            Context context = new Context();
            context.setVariable("test_key", "test_value");

            // 메일 내용
            String html = templateEngine.process("mail-template", context);
            helper.setText(html, true);

            // 보내기
            javaMailSender.send(message);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return user;
    }

    @GetMapping("/checkEmail/{email}")
    @ApiOperation(value = "이메일확인")
    public String checkEmail(@PathVariable String email) {
        String result;
        Optional<User> userOpt = userDao.findUserByEmail(email);
        if (userOpt.isPresent()) {
            result = "이미 존재하는 이메일입니다.";
        } else {
            result = "사용 가능한 이메일입니다.";
        }
        return result;
    }

    @GetMapping("/checkNickname/{nickname}")
    @ApiOperation(value = "닉네임확인")
    public String checkNickname(@PathVariable String nickname) {
        String result;
        Optional<User> userOpt = userDao.findUserByNickname(nickname);
        if (userOpt.isPresent()) {
            result = "이미 존재하는 닉네임입니다.";
        } else {
            result = "사용 가능한 닉네임입니다.";
        }
        return result;
    }

    @PutMapping("/modify")
    @ApiOperation(value = "회원정보수정")
    public Object modify(@Valid @RequestBody SignupRequest request) throws SQLException, IOException {
        try {
            Optional<User> userOpt = userDao.findUserByEmail(request.getEmail());
            if (userOpt.isPresent()) {
                User newUser = userOpt.get();
                newUser.setName(request.getName());
                newUser.setPassword(request.getPassword());
                newUser.setNickname(request.getNickname());
                userDao.save(newUser);
                return newUser;
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{email}")
    @ApiOperation(value = "탈퇴하기")
    public Object delete(@PathVariable String email) {
        Optional<User> userOpt = userDao.findUserByEmail(email);
        if (userOpt.isPresent()) {
            userDao.delete(userOpt.get());
            return "탈퇴 완료";
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}