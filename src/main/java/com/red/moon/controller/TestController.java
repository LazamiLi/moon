package com.red.moon.controller;

import com.red.moon.utils.Result;
import com.red.moon.utils.ResultUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lihui
 * @Date: 2020/2/21 9:08
 */
@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping("method1")
    public Result method1(String word) {
        System.out.println(word);
        return ResultUtil.success();
    }
}
