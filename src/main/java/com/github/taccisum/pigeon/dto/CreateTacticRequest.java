package com.github.taccisum.pigeon.dto;

import lombok.Data;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1
 */
@Data
public class CreateTacticRequest {
    private Boolean mustTest;
    private long templateId;
    private String targets;
    private String defaultSender;
    private String defaultParams;
}
