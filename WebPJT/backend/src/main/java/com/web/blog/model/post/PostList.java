package com.web.blog.model.post;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;

    private String email;
    private String title;
    private String location;
    private String imgurl;
    private int price;
    private LocalDate sdate;
    private LocalDate edate;
    private String companyInfo;
    private String detail;
    private int flag;
    private String activity;

    @JsonIgnore
    private LocalDateTime createDate;
}