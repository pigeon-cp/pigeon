package com.github.taccisum.pigeon.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.taccisum.pigeon.dao.data.MessageDOImpl;
import com.github.taccisum.pigeon.dao.mapper.MessageMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import pigeon.core.dao.MessageDAO;
import pigeon.core.data.MessageDO;

import java.io.Serializable;
import java.util.List;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1
 */
@Component
public class MessageDAOImpl extends BaseMapperDAOImpl<MessageDO, MessageDOImpl, MessageMapper> implements MessageDAO {
    public MessageDAOImpl(MessageMapper mapper) {
        super(mapper);
    }

    @Override
    public <ID extends Serializable> ID insert0(MessageDOImpl data) {
        setDefault(data);
        return super.insert0(data);
    }

    @Override
    public void insertAll(List<MessageDO> list) {
        for (MessageDO item : list) {
            setDefault(item);
        }

        mapper.insertBatch(list);
    }

    private void setDefault(MessageDO data) {
        if (data.getSender() == null) {
            data.setSender("");
        }
        if (data.getSpAccountId() == null) {
            data.setSpAccountId(-1L);
        }
    }

    @Override
    public void updateMassIdBatch(Long massId, Long subMassId, List<Long> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        MessageDOImpl o = this.newEmptyDataObject();
        o.setMassId(massId);
        o.setSubMassId(subMassId);
        mapper.update(o, new LambdaUpdateWrapper<MessageDOImpl>()
                .in(MessageDO::getId, list)
        );
    }

    @Override
    public void updateBatchByIdList(MessageDO o, List<Long> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        o.setId(null);
        try {
            mapper.update((MessageDOImpl) o, new LambdaUpdateWrapper<MessageDOImpl>()
                    .in(MessageDO::getId, list)
            );
        } catch (ClassCastException e) {
            throw new UnsupportedOperationException(o.getClass().getSimpleName());
        }
    }

    @Override
    public List<? extends MessageDO> selectListByMassId(Long massId, long limit) {
        return mapper.selectList(new LambdaQueryWrapper<MessageDOImpl>()
                .eq(MessageDOImpl::getMassId, massId)
                .last("LIMIT " + limit)
        );
    }

    @Override
    public List<? extends MessageDO> selectListBySubMassId(Long subMassId) {
        return mapper.selectList(new LambdaQueryWrapper<MessageDOImpl>()
                .eq(MessageDOImpl::getSubMassId, subMassId)
        );
    }

    @Override
    public MessageDOImpl newEmptyDataObject() {
        return new MessageDOImpl();
    }
}
