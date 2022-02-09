package com.github.taccisum.pigeon.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.taccisum.pigeon.core.dao.MessageDAO;
import com.github.taccisum.pigeon.core.data.MessageDO;
import com.github.taccisum.pigeon.dao.mapper.MessageMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1
 */
@Component
public class MessageDAOImpl implements MessageDAO {
    @Resource
    private MessageMapper mapper;

    @Override
    public void insertAll(List<MessageDO> list) {
        mapper.insertBatch(list);
    }

    @Override
    public void updateMassIdBatch(Long massId, Long subMassId, List<Long> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        MessageDO o = new MessageDO();
        o.setMassId(massId);
        o.setSubMassId(subMassId);
        mapper.update(o, new LambdaUpdateWrapper<MessageDO>()
                .in(MessageDO::getId, list)
        );
    }

    @Override
    public List<MessageDO> selectListByMassId(Long massId, long limit) {
        return mapper.selectList(new LambdaQueryWrapper<MessageDO>()
                .eq(MessageDO::getMassId, massId)
                .last("LIMIT " + limit)
        );
    }

    @Override
    public List<MessageDO> selectListBySubMassId(Long subMassId) {
        return mapper.selectList(new LambdaQueryWrapper<MessageDO>()
                .eq(MessageDO::getSubMassId, subMassId)
        );
    }

    @Override
    public <ID extends Serializable> ID insert(MessageDO data) {
        mapper.insert(data);
        return (ID) data.getId();
    }

    @Override
    public MessageDO selectById(Serializable id) {
        return mapper.selectById(id);
    }

    @Override
    public void updateById(MessageDO o) {
        mapper.updateById(o);
    }

    @Override
    public MessageDO newEmptyDataObject() {
        return new MessageDO();
    }
}
