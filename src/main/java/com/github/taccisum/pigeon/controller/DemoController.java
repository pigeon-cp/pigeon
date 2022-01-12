package com.github.taccisum.pigeon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2022-01-12 02:52:58 PM Wed
 */
@RestController
@RequestMapping("demo")
public class DemoController {
    @GetMapping
    public String index() {
        return "hello";
    }
}
