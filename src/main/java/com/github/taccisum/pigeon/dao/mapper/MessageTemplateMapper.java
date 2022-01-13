package com.github.taccisum.pigeon.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.taccisum.pigeon.core.dao.MessageTemplateDAO;
import com.github.taccisum.pigeon.core.data.MessageTemplateDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1
 */
@Mapper
public interface MessageTemplateMapper extends MessageTemplateDAO, BaseMapper<MessageTemplateDO> {
}
