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
public class CreateMessageTemplateRequest {
    @Extensible(ExtensionType.MESSAGE_TYPE)
    @ApiModelProperty(value = "模板消息类型", required = true)
    private String type;
    @ApiModelProperty(value = "模板占位规则类型", required = true, example = "LOCAL:SIMPLE")
    private String placeholderRule;
    @Extensible(ExtensionType.SERVICE_PROVIDER_TYPE)
    @ApiModelProperty(value = "模板所关联的第三方服务商", required = true)
    private String spType;
    @ApiModelProperty(value = "模板所关联的三方服务商账号 id", required = true)
    private Long spAccountId;
    @ApiModelProperty(value = "此消息模板所关联的在第三方服务商处维护的编码", required = true)
    private String thirdCode;
    @ApiModelProperty(value = "模板标题", required = true)
    private String title;
    @ApiModelProperty(value = "模板内容", required = true)
    private String content;
    @ApiModelProperty(value = "发送时使用的标签", required = true)
    private String tag;
}
