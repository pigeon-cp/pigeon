package com.github.taccisum.pigeon.domain.repo.factory.template;

import com.github.taccisum.pigeon.core.entity.core.MessageTemplate;
import com.github.taccisum.pigeon.core.repo.factory.MessageTemplateFactory;
import com.github.taccisum.pigeon.domain.entity.template.MailTemplate;
import org.springframework.stereotype.Component;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2022/1/12
 */
@Component
public class DefaultMessageTemplateFactory implements MessageTemplateFactory {
    @Override
    public MessageTemplate create(Long id) {
        return new MailTemplate(id);
    }

    @Override
    public boolean match(Long id, Args o) {
        return true;
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }
}
