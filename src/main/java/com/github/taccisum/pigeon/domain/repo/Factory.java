package com.github.taccisum.pigeon.domain.repo;

import com.github.taccisum.pigeon.domain.entity.core.Message;
import com.github.taccisum.pigeon.domain.entity.core.MessageTemplate;
import com.github.taccisum.pigeon.domain.entity.core.ServiceProvider;
import com.github.taccisum.pigeon.domain.entity.core.ThirdAccount;
import com.github.taccisum.pigeon.domain.entity.message.AliCloudMailMessage;
import com.github.taccisum.pigeon.domain.entity.message.template.MailTemplate;
import com.github.taccisum.pigeon.domain.entity.sp.AliCloud;
import com.github.taccisum.pigeon.domain.entity.sp.AliCloudAccount;
import org.springframework.stereotype.Component;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1
 */
@Component
public class Factory implements com.github.taccisum.domain.core.Factory {
    public MessageTemplate createMessageTemplate(long id) {
        return new MailTemplate(id);
    }

    public Message createMessage(long id, Message.Type type, ServiceProvider.Type spType) {
        return new AliCloudMailMessage(id);
    }

    public ServiceProvider createServiceProvider(ServiceProvider.Type id) {
        switch (id) {
            case ALI_CLOUD:
                return new AliCloud();
            default:
                throw new UnsupportedOperationException(id.name());
        }
    }

    public ThirdAccount createThirdAccount(long id, ServiceProvider.Type spType) {
        switch (spType) {
            case ALI_CLOUD:
                return new AliCloudAccount(id);
            default:
                throw new UnsupportedOperationException(spType.name());
        }
    }
}
