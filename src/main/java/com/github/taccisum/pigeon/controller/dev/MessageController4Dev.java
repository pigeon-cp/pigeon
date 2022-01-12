package com.github.taccisum.pigeon.controller.dev;

import com.github.taccisum.pigeon.domain.entity.core.Message;
import com.github.taccisum.pigeon.domain.entity.core.MessageTemplate;
import com.github.taccisum.pigeon.domain.repo.MessageTemplateRepo;
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
    public long send(@PathVariable Long templateId, @RequestParam String target, @RequestParam List<String> params) {
        MessageTemplate template = messageTemplateRepo.getOrThrow(templateId);
        Message message = template.initMessage("robot_01@smtp.66cn.top", target, params);
        message.deliver();
        return message.id();
    }
}
