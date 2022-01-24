package com.github.taccisum.pigeon.controller;

import com.github.taccisum.pigeon.core.data.MessageDO;
import com.github.taccisum.pigeon.core.entity.core.Message;
import com.github.taccisum.pigeon.core.entity.core.MessageTemplate;
import com.github.taccisum.pigeon.core.entity.core.User;
import com.github.taccisum.pigeon.core.entity.core.template.MailTemplate;
import com.github.taccisum.pigeon.core.entity.core.template.SMSTemplate;
import com.github.taccisum.pigeon.core.repo.MessageRepo;
import com.github.taccisum.pigeon.core.repo.MessageTemplateRepo;
import com.github.taccisum.pigeon.core.repo.UserRepo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1.1
 */
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
    public long send(@PathVariable String type,
                     @RequestParam(required = false) String sender,
                     @RequestParam(required = false) String target,
                     @RequestParam String title,
                     @RequestParam String content,
                     @RequestParam String spType,
                     @RequestParam Long spAccountId
    ) {
        if (sender == null) {
//            if (template instanceof MailTemplate) {
//                sender = "robot_01@smtp.66cn.top";
//            } else if (template instanceof SMSTemplate) {
//                sender = "sms_robot.pigeon";
//            } else {
            sender = "pigeon";
//            }
        }

        User user = null;
        if (target.startsWith("u_")) {
            user = userRepo.getOrThrow(target.replaceAll("^u_", ""));
        }
        MessageDO o = new MessageDO();
        o.setType(type);
        o.setSpType(spType);
        o.setSpAccountId(spAccountId);
        o.setSender(sender);
        if (user != null) {
            o.setTarget(user.getAccountFor(type));
            o.setTargetUserId(user.id());
        } else {
            o.setTarget(target);
        }

        o.setTitle(title);
        o.setContent(content);
        o.setParams("{}");
        o.setTemplateId(null);
        o.setTag("");
        Message message = messageRepo.create(o);

        message.deliver();
        return message.id();
    }

    @ApiOperation("发送一条模板消息")
    @PostMapping
    public long send(
            @RequestParam Long templateId,
            @RequestParam(required = false) String sender,
            @RequestParam String target,
            @RequestParam(required = false) String params
    ) {
        User user = null;
        if (target.startsWith("u_")) {
            user = userRepo.getOrThrow(target.replaceAll("^u_", ""));
        }

        MessageTemplate template = messageTemplateRepo.getOrThrow(templateId);
        Message message;

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
            message = template.initMessage(sender, target, params);
        } else {
            message = template.initMessage(sender, user, params);
        }
        message.deliver();
        return message.id();
    }
}
