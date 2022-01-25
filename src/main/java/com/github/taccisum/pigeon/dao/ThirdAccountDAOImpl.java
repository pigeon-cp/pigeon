package com.github.taccisum.pigeon.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.taccisum.pigeon.core.dao.ThirdAccountDAO;
import com.github.taccisum.pigeon.core.data.ThirdAccountDO;
import com.github.taccisum.pigeon.dao.mapper.ThirdAccountMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.2
 */
@Component
public class ThirdAccountDAOImpl implements ThirdAccountDAO {
    @Resource
    private ThirdAccountMapper mapper;

    @Override
    public List<ThirdAccountDO> selectByUsername(String name) {
        return mapper.selectList(new LambdaQueryWrapper<ThirdAccountDO>()
                .eq(ThirdAccountDO::getUsername, name)
        );
    }

    @Override
    public <ID extends Serializable> ID insert(ThirdAccountDO data) {
        mapper.insert(data);
        return (ID) data.getId();
    }

    @Override
    public ThirdAccountDO selectById(Serializable id) {
        return mapper.selectById(id);
    }

    @Override
    public void updateById(ThirdAccountDO o) {
        mapper.updateById(o);
    }

    @Override
    public ThirdAccountDO newEmptyDataObject() {
        return new ThirdAccountDO();
    }
}
