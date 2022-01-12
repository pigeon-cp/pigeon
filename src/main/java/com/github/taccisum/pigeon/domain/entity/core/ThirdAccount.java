package com.github.taccisum.pigeon.domain.entity.core;

import com.github.taccisum.domain.core.Entity;
import com.github.taccisum.pigeon.dao.mapper.ThirdAccountMapper;
import com.github.taccisum.pigeon.domain.data.ThirdAccountDO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 三方账号
 *
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1
 */
public class ThirdAccount extends Entity.Base<Long> {
    @Autowired
    private ThirdAccountMapper dao;

    public ThirdAccount(long id) {
        super(id);
    }

    public ThirdAccountDO data() {
        return dao.selectById(this.id());
    }
}
