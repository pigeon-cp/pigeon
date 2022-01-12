package com.github.taccisum.pigeon.domain.data;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.taccisum.pigeon.domain.entity.core.ServiceProvider;
import lombok.Data;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1
 */
@Data
@TableName("message_template")
public class MessageTemplateDO {
    private Long id;
    /**
     * 模板所关联的第三方服务商
     */
    private ServiceProvider.Type spType;
    /**
     * 模板所关联的三方服务商账号 id
     */
    private Long spAccountId;
    /**
     * 此消息模板所关联的在第三方服务商处维护的编码
     */
    private String thirdCode;
    /**
     * 模板标题（快照）
     */
    private String titleSnapshot;
    /**
     * 模板内容（快照）
     */
    private String contentSnapshot;
    /**
     * 发送时使用的标签
     */
    private String tag;
}
