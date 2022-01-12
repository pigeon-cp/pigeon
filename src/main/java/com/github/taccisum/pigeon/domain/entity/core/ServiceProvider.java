package com.github.taccisum.pigeon.domain.entity.core;

import com.github.taccisum.domain.core.Entity;

import static com.github.taccisum.pigeon.domain.entity.core.ServiceProvider.Type;

/**
 * 服务提供商
 *
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1
 */
public interface ServiceProvider extends Entity<ServiceProvider.Type> {
    default Type getType() {
        return this.id();
    }

    enum Type {
        /**
         * 阿里云
         */
        ALI_CLOUD,
    }
}
