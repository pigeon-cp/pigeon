package com.github.taccisum.pigeon.controller.dev;

import com.github.taccisum.pigeon.core.entity.core.Message;
import com.github.taccisum.pigeon.core.entity.core.MessageTemplate;
import com.github.taccisum.pigeon.core.entity.core.template.MailTemplate;
import com.github.taccisum.pigeon.core.entity.core.template.SMSTemplate;
import com.github.taccisum.pigeon.core.repo.MessageTemplateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1
 */
@Profile({"local", "dev"})
@RequestMapping("/dev/messages")
@RestController
public class MessageController4Dev {
    @Autowired
    private MessageTemplateRepo messageTemplateRepo;

    @PostMapping("/templates/{templateId}")
    public long send(@PathVariable Long templateId, @RequestParam String target, @RequestParam(required = false) String params) {
        MessageTemplate template = messageTemplateRepo.getOrThrow(templateId);
        Message message;
        if (template instanceof MailTemplate) {
            message = template.initMessage("robot_01@smtp.66cn.top", target, params);
        } else if (template instanceof SMSTemplate) {
            message = template.initMessage("sms_robot.pigeon", target, params);
        } else {
            message = template.initMessage(null, target, params);
        }
        message.deliver();
        return message.id();
    }
}
