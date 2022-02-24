package com.github.taccisum.pigeon.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pigeon.core.docs.ExtensionType;
import pigeon.core.docs.springfox.Extensible;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.2
 */
@Data
public class SendMessageRequest {
    @ApiModelProperty("消息发送者")
    private String sender;
    @ApiModelProperty("消息接收者")
    private String target;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("内容")
    private String content;
    @Extensible(ExtensionType.SERVICE_PROVIDER_TYPE)
    @ApiModelProperty("发送渠道")
    private String channel;
    @ApiModelProperty("渠道所用账号 id")
    private Long accountId;
}
