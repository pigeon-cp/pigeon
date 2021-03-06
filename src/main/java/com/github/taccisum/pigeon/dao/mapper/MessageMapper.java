package com.github.taccisum.pigeon.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.taccisum.pigeon.dao.data.MessageDOImpl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pigeon.core.data.MessageDO;

import java.util.List;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1
 */
@Mapper
public interface MessageMapper extends BaseMapper<MessageDOImpl> {
    int insertBatch(@Param("messages") List<MessageDO> messages);
}
