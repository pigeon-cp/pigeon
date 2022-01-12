package com.github.taccisum.pigeon.domain.data;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.taccisum.pigeon.domain.entity.core.Message;
import com.github.taccisum.pigeon.domain.entity.core.ServiceProvider;
import lombok.Data;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1
 */
@Data
@TableName("message")
public class MessageDO {
    private Long id;
    /**
     * 发送人
     */
    private String sender;
    /**
     * 推送目标
     */
    private String target;
    /**
     * 消息类型
     */
    private Message.Type type;
    /**
     * 服务商类型
     */
    private ServiceProvider.Type spType;
    /**
     * 服务商账号 id
     */
    private Long spAccountId;
    /**
     * 标题
     */
    private String title;
    /**
     * 正文
     */
    private String content;
    /**
     * 模板 id
     */
    private Long templateId;
    /**
     * 参数
     */
    private String params;
    /**
     * 标签
     */
    private String tag;
    /**
     * 消息状态
     */
    private Message.Status status;
}
