package com.github.taccisum.pigeon.dao;

import pigeon.core.dao.MassTacticDAO;
import pigeon.core.data.MassTacticDO;
import com.github.taccisum.pigeon.dao.data.MassTacticDOImpl;
import com.github.taccisum.pigeon.dao.mapper.MassTacticMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1
 */
@Component
public class MassTacticDAOImpl implements MassTacticDAO {
    @Resource
    private MassTacticMapper mapper;

    @Override
    public <ID extends Serializable> ID insert(MassTacticDO data) {
        if (data instanceof MassTacticDOImpl) {
            mapper.insert((MassTacticDOImpl) data);
            return (ID) data.getId();
        }

        throw new UnsupportedOperationException(data.getClass().getSimpleName());
    }

    @Override
    public MassTacticDO selectById(Serializable id) {
        return mapper.selectById(id);
    }

    @Override
    public void updateById(MassTacticDO o) {
        if (o instanceof MassTacticDOImpl) {
            mapper.updateById((MassTacticDOImpl) o);
            return;
        }

        throw new UnsupportedOperationException(o.getClass().getSimpleName());
    }

    @Override
    public MassTacticDO newEmptyDataObject() {
        return new MassTacticDOImpl();
    }
}
