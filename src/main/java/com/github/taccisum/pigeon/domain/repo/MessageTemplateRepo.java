package com.github.taccisum.pigeon.domain.repo;

import com.github.taccisum.domain.core.exception.DataNotFoundException;
import com.github.taccisum.pigeon.dao.mapper.MessageTemplateMapper;
import com.github.taccisum.pigeon.domain.data.MessageTemplateDO;
import com.github.taccisum.pigeon.domain.entity.core.MessageTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1
 */
@Component
public class MessageTemplateRepo {
    @Autowired
    private MessageTemplateMapper mapper;
    @Autowired
    private Factory factory;

    public Optional<MessageTemplate> get(long id) {
        MessageTemplateDO data = mapper.selectById(id);
        if (data == null) {
            return Optional.empty();
        }
        return Optional.of(factory.createMessageTemplate(data.getId()));
    }

    public MessageTemplate getOrThrow(long id) throws MessageTemplateNotFoundException {
        return this.get(id)
                .orElseThrow(() -> new MessageTemplateNotFoundException(id));
    }

    public static class MessageTemplateNotFoundException extends DataNotFoundException {
        public MessageTemplateNotFoundException(long id) {
            super("消息模板", id);
        }
    }
}
