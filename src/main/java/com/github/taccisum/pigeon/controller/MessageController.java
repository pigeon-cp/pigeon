package com.github.taccisum.pigeon.controller;

import com.github.taccisum.pigeon.dto.SendMessageRequest;
import com.github.taccisum.pigeon.dto.SendTemplateMessageRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pigeon.core.data.MessageDO;
import pigeon.core.entity.core.Message;
import pigeon.core.entity.core.MessageTemplate;
import pigeon.core.entity.core.User;
import pigeon.core.entity.core.template.MailTemplate;
import pigeon.core.entity.core.template.SMSTemplate;
import pigeon.core.repo.MessageRepo;
import pigeon.core.repo.MessageTemplateRepo;
import pigeon.core.repo.UserRepo;
import pigeon.core.utils.JsonUtils;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1.1
 */
@Validated
@RequestMapping("/messages")
@RestController
@Transactional
public class MessageController {
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private MessageTemplateRepo messageTemplateRepo;
    @Autowired
    private UserRepo userRepo;

    @ApiOperation("发送一条消息")
    @PostMapping("{type}")
    public long send(@PathVariable String type, @RequestBody SendMessageRequest dto) {
        User user = null;
        String target = dto.getTarget();
        if (target.startsWith("u_")) {
            user = userRepo.getOrThrow(target.replaceAll("^u_", ""));
        }
        MessageDO o = new MessageDO();
        o.setType(type);
        o.setSpType(dto.getChannel());
        o.setSpAccountId(dto.getAccountId());
        o.setSender(dto.getSender());
        if (user != null) {
            o.setTarget(user.getAccountFor(type));
            o.setTargetUserId(user.id());
        } else {
            o.setTarget(target);
        }

        o.setTitle(dto.getTitle());
        o.setContent(dto.getContent());
        o.setParams("{}");
        o.setTemplateId(null);
        o.setTag("");
        Message message = messageRepo.create(o);

        message.deliver();
        return message.id();
    }

    @ApiOperation("发送一条模板消息")
    @PostMapping
    public long send(@RequestParam Long templateId, @RequestBody SendTemplateMessageRequest dto) {
        User user = null;
        if (dto.getTarget().startsWith("u_")) {
            user = userRepo.getOrThrow(dto.getTarget().replaceAll("^u_", ""));
        }

        MessageTemplate template = messageTemplateRepo.getOrThrow(templateId);
        Message message;

        String sender = dto.getSender();
        if (sender == null) {
            if (template instanceof MailTemplate) {
                sender = "robot_01@smtp.66cn.top";
            } else if (template instanceof SMSTemplate) {
                sender = "sms_robot.pigeon";
            } else {
                sender = "pigeon";
            }
        }

        if (user == null) {
            message = template.initMessage(sender, dto.getTarget(), dto.getParams(), dto.getSignature(), JsonUtils.stringify(dto.getExt()));
        } else {
            message = template.initMessage(sender, user, dto.getParams(), dto.getSignature(), JsonUtils.stringify(dto.getExt()));
        }
        message.deliver();
        return message.id();
    }
}
