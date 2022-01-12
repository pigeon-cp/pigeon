package com.github.taccisum.pigeon.domain.entity.core;

import com.github.taccisum.domain.core.Entity;
import com.github.taccisum.domain.core.Event;
import com.github.taccisum.pigeon.dao.mapper.MessageMapper;
import com.github.taccisum.pigeon.domain.data.MessageDO;
import com.github.taccisum.pigeon.domain.entity.core.sp.MessageServiceProvider;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 代表一条具体的消息，可以是短信、推送、微信等等
 *
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1
 */
public abstract class Message extends Entity.Base<Long> {
    @Autowired
    protected MessageMapper dao;

    public Message(Long id) {
        super(id);
    }

    public MessageDO data() {
        return this.dao.selectById(this.id());
    }

    /**
     * <pre>
     * 将此条消息投递到第三方服务商
     *
     * published events:
     * {@link DeliverEvent}
     * </pre>
     *
     * @return 投递结果
     */
    public abstract boolean deliver();

    /**
     * 获取此消息关联的服务提供商
     */
    protected abstract MessageServiceProvider getServiceProvider();

    /**
     * 更新消息状态
     *
     * @param status 目标状态
     */
    protected void updateStatus(Status status) {
        MessageDO o = new MessageDO();
        o.setId(this.id());
        o.setStatus(status);
        this.dao.updateById(o);
    }

    /**
     * 标记消息为已发送
     *
     * @param success 是否成功
     */
    public void markSent(boolean success) {
        this.updateStatus(success ? Status.SENT : Status.FAIL);
    }

    /**
     * TODO:: domain-core 存在 bug，当类多重继承时无法解析到父类的泛型 class type，导致无法自动为事件注入主体，修复好之前无法用这个基类简化代码
     */
    protected static abstract class BaseEvent extends Event.Base<Message> {
    }

    /**
     * 消息投递事件（表示已投递到三方服务商，但不一定已发送）
     */
    public static class DeliverEvent extends Event.Base<Message> {
        /**
         * 表示是否投递成功
         */
        @Getter
        private Boolean success;

        public DeliverEvent(Boolean success) {
            this.success = success;
        }
    }

    /**
     * 消息已发送事件（三方服务商已将消息推送给用户，可能成功也可能失败）
     */
    public static class SentEvent extends Event.Base<Message> {
        /**
         * 表示是否发送成功
         */
        @Getter
        private Boolean success;

        public SentEvent(Boolean success) {
            this.success = success;
        }
    }

    /**
     * 消息类型
     */
    public enum Type {
        /**
         * 邮件
         */
        MAIL
    }

    /**
     * 邮件状态
     */
    public enum Status {
        /**
         * 未发送
         */
        NOT_SEND,
        /**
         * 已投递
         */
        DELIVERED,
        /**
         * 已发送
         */
        SENT,
        /**
         * 发送失败
         */
        FAIL,
    }
}
