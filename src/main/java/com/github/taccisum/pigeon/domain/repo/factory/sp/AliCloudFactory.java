package com.github.taccisum.pigeon.domain.repo.factory.sp;

import com.github.taccisum.pigeon.core.entity.core.ServiceProvider;
import com.github.taccisum.pigeon.core.repo.factory.ServiceProviderFactory;
import com.github.taccisum.pigeon.domain.entity.sp.AliCloud;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2022/1/12
 */
@Component
public class AliCloudFactory implements ServiceProviderFactory {
    @Override
    public ServiceProvider create(String id) {
        return new AliCloud();
    }

    @Override
    public boolean match(String id, Args o) {
        return Objects.equals(o.getSpType(), "ALI_CLOUD");
    }
}
