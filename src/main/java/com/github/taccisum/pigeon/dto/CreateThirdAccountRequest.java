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
public class CreateThirdAccountRequest {
    @Extensible(ExtensionType.THIRD_ACCOUNT_TYPE)
    @ApiModelProperty(value = "三方账号类型", required = true)
    private String type;
    @Extensible(ExtensionType.SERVICE_PROVIDER_TYPE)
    @ApiModelProperty(value = "三方服务商类型", required = true)
    private String spType;
    @ApiModelProperty(value = "用户名", required = true)
    private String username;
    @ApiModelProperty("appid")
    private String appId;
    @ApiModelProperty("app_secret")
    private String appSecret;
    @ApiModelProperty("access_token")
    private String accessToken;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("扩展参数")
    private String ext;
}
