package com.github.taccisum.pigeon.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.2
 */
@Data
public class SendTemplateMessageRequest {
    @ApiModelProperty(value = "消息发送者")
    private String sender;
    @NotNull
    @ApiModelProperty(value = "消息目标对象，以 u_ 开头表示其是一个用户", required = true)
    private String target;
    @ApiModelProperty("模板参数，String | Object")
    private Object params;
    @ApiModelProperty("消息签名")
    private String signature;
    @ApiModelProperty("消息扩展参数，视具体消息类型不同而不同")
    private Map<String, Object> ext;
}
