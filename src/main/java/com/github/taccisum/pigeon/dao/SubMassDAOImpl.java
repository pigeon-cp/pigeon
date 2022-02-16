package com.github.taccisum.pigeon.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import pigeon.core.dao.SubMassDAO;
import pigeon.core.data.SubMassDO;
import com.github.taccisum.pigeon.dao.data.SubMassDOImpl;
import com.github.taccisum.pigeon.dao.mapper.SubMassMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.2
 */
@Component
public class SubMassDAOImpl implements SubMassDAO {
    @Resource
    private SubMassMapper mapper;

    @Override
    public <ID extends Serializable> ID insert(SubMassDO data) {
        mapper.insert((SubMassDOImpl) data);
        return (ID) data.getId();
    }

    @Override
    public SubMassDO selectById(Serializable id) {
        return mapper.selectById(id);
    }

    @Override
    public void updateById(SubMassDO o) {
        mapper.updateById((SubMassDOImpl) o);
    }

    @Override
    public SubMassDO newEmptyDataObject() {
        return new SubMassDOImpl();
    }

    @Override
    public List<SubMassDO> selectByMainId(Long mainId) {
        return new ArrayList<>(mapper
                .selectList(new LambdaQueryWrapper<SubMassDOImpl>()
                        .eq(SubMassDO::getMainId, mainId)
                ));
    }
}
