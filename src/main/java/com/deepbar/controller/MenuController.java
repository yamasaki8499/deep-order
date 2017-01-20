package com.deepbar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

/**
 * Created by rayl on 2017/1/20.
 */
@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired

    @RequestMapping("query")
    @ResponseBody
    public String getMenu(HttpServletRequest request){
        return "";
    }

}
