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
    @ApiModelProperty
    private String sender;
    @NotNull
    @ApiModelProperty(required = true)
    private String target;
    @ApiModelProperty
    private String params;
    @ApiModelProperty
    private String signature;
    @ApiModelProperty
    private Map<String, Object> ext;
}
