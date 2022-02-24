package com.github.taccisum.pigeon.controller;

import com.github.taccisum.pigeon.dao.data.MessageTemplateDOImpl;
import com.github.taccisum.pigeon.dto.CreateMessageTemplateRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pigeon.core.repo.MessageTemplateRepo;

import javax.annotation.Resource;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.2
 */
@Validated
@RestController
@Transactional
@RequestMapping("/message-templates")
public class MessageTemplateController {
    @Resource
    private MessageTemplateRepo repo;

    @PostMapping
    public long create(@RequestBody CreateMessageTemplateRequest body) {
        MessageTemplateDOImpl data = new MessageTemplateDOImpl();
        BeanUtils.copyProperties(body, data);
        return repo.create(data).id();
    }
}
