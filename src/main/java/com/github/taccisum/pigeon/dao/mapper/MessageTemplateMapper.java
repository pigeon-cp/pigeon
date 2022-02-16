package com.github.taccisum.pigeon.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pigeon.core.dao.MessageTemplateDAO;
import pigeon.core.data.MessageTemplateDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1
 */
@Mapper
public interface MessageTemplateMapper extends MessageTemplateDAO, BaseMapper<MessageTemplateDO> {
}
