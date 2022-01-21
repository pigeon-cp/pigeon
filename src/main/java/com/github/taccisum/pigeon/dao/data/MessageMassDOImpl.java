package com.github.taccisum.pigeon.dao.data;

import com.baomidou.mybatisplus.annotation.*;
import com.github.taccisum.pigeon.core.data.MessageMassDO;
import lombok.Data;

import java.util.Date;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1
 */
@Data
@TableName("message_mass")
public class MessageMassDOImpl extends MessageMassDO {
    @TableId(type = IdType.AUTO)
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @TableField(fill = FieldFill.INSERT)
    private Date createdAt;
}
