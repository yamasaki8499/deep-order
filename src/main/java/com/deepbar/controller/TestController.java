package com.deepbar.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rayl on 2017/1/17.
 */
@RestController
@RequestMapping("test")
public class TestController {
    @RequestMapping("method")
    @ResponseBody
    public String testMehod(){
        return "hello world!";
    }
}
