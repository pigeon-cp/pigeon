package com.github.taccisum.pigeon.domain.entity.message;

import com.github.taccisum.domain.core.exception.DataErrorException;
import com.github.taccisum.pigeon.domain.data.MessageDO;
import com.github.taccisum.pigeon.domain.entity.core.sp.MailServiceProvider;
import com.github.taccisum.pigeon.domain.entity.sp.AliCloud;
import com.github.taccisum.pigeon.domain.entity.sp.AliCloudAccount;
import lombok.extern.slf4j.Slf4j;

import java.rmi.ServerException;

/**
 * 阿里云邮件服务消息
 *
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1
 */
@Slf4j
public class AliCloudMailMessage extends MailMessage {
    public AliCloudMailMessage(Long id) {
        super(id);
    }

    @Override
    public boolean deliver() {
        boolean success = false;
        try {
            MessageDO data = this.data();
            this.getServiceProvider()
                    .getAccount(data.getSpAccountId())
                    .sendMailVia(
                            data.getSender(),
                            data.getTarget(),
                            data.getTitle(),
                            data.getContent(),
                            data.getTag(),
                            null
                    );
            success = true;
        } catch (AliCloudAccount.MailSendException e) {
            success = false;
            if (e.getCause() instanceof ServerException) {
                log.warn(String.format("消息 %d 发送失败，阿里云服务端返回异常", this.id()), e);
            } else {
                log.error(String.format("消息 %d 发送失败", this.id()), e);
            }
        }
        this.updateStatus(success ? Status.DELIVERED : Status.FAIL);
        this.publish(new DeliverEvent(success));
        return success;
    }

    @Override
    protected AliCloud getServiceProvider() {
        MailServiceProvider sp = super.getServiceProvider();
        if (sp instanceof AliCloud) {
            return (AliCloud) sp;
        }
        throw new DataErrorException("AliCloudMailMessage.ServiceProvider", this.id(), "阿里云邮件消息可能关联了错误的服务提供商：" + sp.getType().name() + "，请检查数据是否异常");
    }
}
