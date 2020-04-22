package com.demo.swagger.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/controller/user")
public class UserController {

    @ApiOperation(value = "controller查询用户名称")
    @PostMapping("/name")
    private String name(@RequestHeader(name = "X-WEB-TOKEN") String token,
                        HttpServletRequest request,
                        @RequestParam(name = "name") final String name) {
        return "controller user say hello to: "+ name;
    }
}
