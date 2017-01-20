package com.deepbar.controller;

import com.deepbar.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rayl on 2017/1/17.
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("method")
    @ResponseBody
    public String testMehod(){
        return "hello world!";
    }

    @RequestMapping("testUser")
    @ResponseBody
    public String testUsers(){
        return userRepository.findAll().toString();
    }
}
