package com.lengao.eureka.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>description goes here</p>
 *
 * @author 冷澳
 * @date 2023/2/23
 */
@RestController
@RequestMapping("service")

@Slf4j
public class HelloController {
    @GetMapping("test/{msg}")
    String test(@PathVariable("msg") String msg) {
        log.info(msg);
        return "hello " + msg;
    }
}
