package com.github.taccisum.pigeon.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pigeon.core.dao.BaseDAO;
import pigeon.core.data.DataObject;

import java.io.Serializable;

/**
 * 基于 mybatis mapper 的 DAO 实现基类
 *
 * @param <O> 原始的 data object 接口
 * @param <R> 真正的 data object 类
 * @param <M> mybatis-plus base mapper for {@link R}
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.2
 */
public abstract class BaseMapperDAOImpl<O extends DataObject, R extends O, M extends BaseMapper<R>> implements BaseDAO<O> {
    protected M mapper;

    public BaseMapperDAOImpl(M mapper) {
        this.mapper = mapper;
    }

    public <ID extends Serializable> ID insert0(R data) {
        mapper.insert(data);
        return (ID) data.getId();
    }

    @Override
    public <ID extends Serializable> ID insert(O data) {
        try {
            return insert0((R) data);
        } catch (ClassCastException e) {
            throw new UnsupportedOperationException(data.getClass().getSimpleName());
        }
    }

    @Override
    public O selectById(Serializable id) {
        return mapper.selectById(id);
    }

    public void updateById0(R data) {
        mapper.updateById(data);
    }

    @Override
    public void updateById(O data) {
        try {
            updateById0((R) data);
        } catch (ClassCastException e) {
            throw new UnsupportedOperationException(data.getClass().getSimpleName());
        }
    }

    @Override
    public abstract R newEmptyDataObject();
}
