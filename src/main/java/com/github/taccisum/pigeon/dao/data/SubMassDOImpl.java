package com.github.taccisum.pigeon.dao.data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import pigeon.core.data.SubMassDO;
import lombok.Data;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.2
 */
@Data
@TableName("sub_mass")
public class SubMassDOImpl extends SubMassDO {
    @TableId(type = IdType.AUTO)
    private Long id;
}
