package com.github.taccisum.pigeon.dao.data;

import com.baomidou.mybatisplus.annotation.*;
import com.github.taccisum.pigeon.core.data.MassTacticDO;
import lombok.Data;

import java.util.Date;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1
 */
@Data
@TableName("mass_tactic")
public class MassTacticDOImpl extends MassTacticDO {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField(fill = FieldFill.INSERT)
    private Date createdAt;
}
