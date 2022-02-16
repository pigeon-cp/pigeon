package com.github.taccisum.pigeon.dao;

import pigeon.core.dao.MessageMassDAO;
import pigeon.core.data.MessageMassDO;
import com.github.taccisum.pigeon.dao.data.MessageMassDOImpl;
import com.github.taccisum.pigeon.dao.mapper.MessageMassMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1
 */
@Component
public class MessageMassDAOImpl implements MessageMassDAO {
    @Resource
    private MessageMassMapper mapper;

    @Override
    public <ID extends Serializable> ID insert(MessageMassDO data) {
        if (data instanceof MessageMassDOImpl) {
            mapper.insert((MessageMassDOImpl) data);
            return (ID) data.getId();
        }

        throw new UnsupportedOperationException(data.getClass().getSimpleName());
    }

    @Override
    public MessageMassDO selectById(Serializable id) {
        return mapper.selectById(id);
    }

    @Override
    public void updateById(MessageMassDO o) {
        if (o instanceof MessageMassDOImpl) {
            mapper.updateById((MessageMassDOImpl) o);
            return;
        }

        throw new UnsupportedOperationException(o.getClass().getSimpleName());
    }

    @Override
    public MessageMassDO newEmptyDataObject() {
        return new MessageMassDOImpl();
    }
}
