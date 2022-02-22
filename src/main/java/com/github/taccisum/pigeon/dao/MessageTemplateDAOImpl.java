package com.github.taccisum.pigeon.dao;

import com.github.taccisum.pigeon.dao.data.MessageTemplateDOImpl;
import com.github.taccisum.pigeon.dao.mapper.MessageTemplateMapper;
import org.springframework.stereotype.Component;
import pigeon.core.dao.MessageTemplateDAO;
import pigeon.core.data.MessageTemplateDO;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.2
 */
@Component
public class MessageTemplateDAOImpl extends BaseMapperDAOImpl<MessageTemplateDO, MessageTemplateDOImpl, MessageTemplateMapper> implements MessageTemplateDAO {
    public MessageTemplateDAOImpl(MessageTemplateMapper mapper) {
        super(mapper);
    }

    @Override
    public MessageTemplateDOImpl newEmptyDataObject() {
        return new MessageTemplateDOImpl();
    }
}
