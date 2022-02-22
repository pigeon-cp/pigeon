package com.github.taccisum.pigeon.dao.data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import pigeon.core.data.MessageTemplateDO;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.2
 */
@Data
@TableName("message_template")
public class MessageTemplateDOImpl extends MessageTemplateDO {
    @TableId(type = IdType.AUTO)
    private Long id;
}
