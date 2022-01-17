package com.github.taccisum.pigeon.controller.dev;

import com.github.taccisum.pigeon.core.entity.core.Message;
import com.github.taccisum.pigeon.core.entity.core.MessageTemplate;
import com.github.taccisum.pigeon.core.entity.core.User;
import com.github.taccisum.pigeon.core.entity.core.template.MailTemplate;
import com.github.taccisum.pigeon.core.entity.core.template.SMSTemplate;
import com.github.taccisum.pigeon.core.repo.MessageTemplateRepo;
import com.github.taccisum.pigeon.core.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/templates/{templateId}")
    public long send(@PathVariable Long templateId, @RequestParam String target, @RequestParam(required = false) String params) {
        User user = null;
        if (target.startsWith("u_")) {
            user = userRepo.getOrThrow(target.replaceAll("^u_", ""));
        }

        MessageTemplate template = messageTemplateRepo.getOrThrow(templateId);
        Message message;
        String sender = null;
        if (template instanceof MailTemplate) {
            sender = "robot_01@smtp.66cn.top";
        } else if (template instanceof SMSTemplate) {
            sender = "sms_robot.pigeon";
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
