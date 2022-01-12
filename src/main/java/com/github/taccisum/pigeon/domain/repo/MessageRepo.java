package com.github.taccisum.pigeon.domain.repo;

import com.github.taccisum.pigeon.dao.mapper.MessageMapper;
import com.github.taccisum.pigeon.domain.data.MessageDO;
import com.github.taccisum.pigeon.domain.entity.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1
 */
@Component
public class MessageRepo {
    @Autowired
    private MessageMapper mapper;
    @Autowired
    private Factory factory;

    public Message create(MessageDO data) {
        mapper.insert(data);
        return factory.createMessage(data.getId(), data.getType(), data.getSpType());
    }
}
