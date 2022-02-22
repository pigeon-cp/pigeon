package com.github.taccisum.pigeon.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.taccisum.pigeon.dao.data.ThirdAccountDOImpl;
import com.github.taccisum.pigeon.dao.mapper.ThirdAccountMapper;
import org.springframework.stereotype.Component;
import pigeon.core.dao.ThirdAccountDAO;
import pigeon.core.data.ThirdAccountDO;

import java.util.List;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.2
 */
@Component
public class ThirdAccountDAOImpl extends BaseMapperDAOImpl<ThirdAccountDO, ThirdAccountDOImpl, ThirdAccountMapper> implements ThirdAccountDAO {
    public ThirdAccountDAOImpl(ThirdAccountMapper mapper) {
        super(mapper);
    }

    @Override
    public List<? extends ThirdAccountDO> selectByUsername(String name) {
        return mapper.selectList(new LambdaQueryWrapper<ThirdAccountDOImpl>()
                .eq(ThirdAccountDOImpl::getUsername, name)
        );
    }

    @Override
    public ThirdAccountDOImpl newEmptyDataObject() {
        return new ThirdAccountDOImpl();
    }
}
