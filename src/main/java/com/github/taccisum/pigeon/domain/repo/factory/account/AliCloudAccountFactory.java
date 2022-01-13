package com.github.taccisum.pigeon.domain.repo.factory.account;

import com.github.taccisum.pigeon.core.entity.core.ThirdAccount;
import com.github.taccisum.pigeon.core.repo.factory.ThirdAccountFactory;
import com.github.taccisum.pigeon.domain.entity.sp.AliCloudAccount;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2022/1/12
 */
@Component
public class AliCloudAccountFactory implements ThirdAccountFactory {
    @Override
    public ThirdAccount create(Long id) {
        return new AliCloudAccount(id);
    }

    @Override
    public boolean match(Long id, Args o) {
        return Objects.equals(o.getSpType(), "ALI_CLOUD");
    }
}
