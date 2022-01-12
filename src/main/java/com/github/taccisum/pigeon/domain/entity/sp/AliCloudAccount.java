package com.github.taccisum.pigeon.domain.entity.sp;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.github.taccisum.pigeon.domain.data.ThirdAccountDO;
import com.github.taccisum.pigeon.domain.entity.core.ThirdAccount;
import com.github.taccisum.pigeon.domain.exception.DomainException;
import lombok.Data;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Optional;

/**
 * 阿里云账号
 *
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1
 */
public class AliCloudAccount extends ThirdAccount {
    private IAcsClient client;

    public AliCloudAccount(Long id) {
        super(id);
    }

    /**
     * 通过阿里云邮件服务指定账号发送一封邮件，详见 https://help.aliyun.com/document_detail/29459.html
     *
     * @param account     发信账号，最终会显示在发信人上（如 robot -> robot@smtp.foo.com）
     * @param toAddresses 目标地址，支持指定多个，收件人之间用逗号隔开
     * @param subject     主题
     * @param htmlBody    内容（支持 HTML）
     * @param tag         邮件标签
     */
    public void sendMailVia(String account, String toAddresses, String subject, String htmlBody, String tag, MailOptions opts) throws
            MailSendException {
        // TODO:: 暂时写死
        AliCloud.Region region = AliCloud.Region.HANG_ZHOU;
        opts = Optional.ofNullable(opts).orElse(new MailOptions());

        try {
            IAcsClient client = this.getClient(region);
            SingleSendMailRequest request = new SingleSendMailRequest();
            if (region != AliCloud.Region.HANG_ZHOU) {
                // 如果是除杭州region外的其它region（如新加坡region）,必须指定为2017-06-22
                request.setVersion("2017-06-22");
            }
            request.setAccountName(account);
            request.setFromAlias(opts.getNickname());
            //0：为随机账号 1：为发信地址
            request.setAddressType(0);
            request.setTagName(tag);
            // 是否启用管理控制台中配置好回信地址（状态须验证通过），取值范围是字符串true或者false
            request.setReplyToAddress(true);

            request.setToAddress(toAddresses);
            //可以给多个收件人发送邮件，收件人之间用逗号分开，批量发信建议使用BatchSendMailRequest方式
            //request.setToAddress("邮箱1,邮箱2");

            request.setSubject(subject);
            request.setHtmlBody(htmlBody);
            request.setMethod(MethodType.POST);
            //如果调用成功，正常返回 httpResponse；如果调用失败则抛出异常，需要在异常中捕获错误异常码；错误异常码请参考对应的API文档;
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
        } catch (ServerException e) {
            throw new MailSendException(e);
        } catch (ClientException e) {
            throw new MailSendException(e);
        }
    }

    private IAcsClient getClient(AliCloud.Region region) {
        if (this.client == null) {
            ThirdAccountDO data = this.data();
            IClientProfile profile = DefaultProfile.getProfile(region.key(), data.getAppId(), data.getAppSecret());

            if (region != AliCloud.Region.HANG_ZHOU) {
                // TODO::
                // 如果是除杭州region外的其它region（如新加坡、澳洲Region），需要将下面的"cn-hangzhou"替换为"ap-southeast-1"、或"ap-southeast-2"。
                // 如果是除杭州region外的其它region（如新加坡region）， 需要做如下处理
                //try {
                //DefaultProfile.addEndpoint("dm.ap-southeast-1.aliyuncs.com", "ap-southeast-1", "Dm",  "dm.ap-southeast-1.aliyuncs.com");
                //} catch (ClientException e) {
                //e.printStackTrace();
                //}
            }
            this.client = new DefaultAcsClient(profile);
        }
        return this.client;
    }

    /**
     * 获取主账号下的子账号实体
     *
     * @param name 子账号名称（用户名即可，示例：sub，会被识别为 sub@main_id.onaliyun.com）
     */
    public AliCloudAccount getSubAccount(String name) {
        throw new NotImplementedException();
    }

    @Data
    public static class MailOptions {
        /**
         * 发信人昵称，长度小于 15 个字符。
         */
        private String nickname;
    }

    /**
     * 阿里云 Open API 访问异常
     */
    public static class OApiAccessException extends DomainException {
        public OApiAccessException(String key, ClientException cause) {
            super(String.format("阿里云 Open API \"%s\" 访问异常，错误码： %s，错误信息：%s", key, cause.getErrCode(), cause.getErrMsg()),
                    cause);
        }
    }

    public static class MailSendException extends OApiAccessException {
        public MailSendException(ClientException cause) {
            super("MailSend", cause);
        }
    }
}
