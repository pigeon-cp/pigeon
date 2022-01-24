package com.github.taccisum.pigeon.controller;

import com.github.taccisum.pigeon.config.ApplicationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1.1
 */
@RestController
@RequestMapping("versions")
public class VersionsController {
    @Resource
    private ApplicationProperties properties;

    @GetMapping
    public String index() {
        return properties.getVersion();
    }
}
