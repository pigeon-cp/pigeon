package com.github.taccisum.pigeon.domain.entity.template;

import com.github.taccisum.pigeon.core.entity.core.Message;
import com.github.taccisum.pigeon.core.entity.core.MessageTemplate;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1
 */
public class MailTemplate extends MessageTemplate {
    public MailTemplate(Long id) {
        super(id);
    }

    @Override
    protected Message.Type getMessageType() {
        return Message.Type.MAIL;
    }
}
