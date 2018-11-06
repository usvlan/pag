package com.gitee.pagesmanager.server.common;

import com.gitee.fastmybatis.core.exception.QueryException;
import com.gitee.fastmybatis.core.mapper.SchMapper;
import com.gitee.fastmybatis.core.query.Query;
import com.gitee.fastmybatis.core.support.PageEasyui;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.gitee.fastmybatis.core.util.MapperUtil.query;

public class MyMapperUtil {

    /**
     * 为easyui表格提供的查询
     *
     * @param mapper mapper
     * @param query  查询条件
     * @param clazz  返回VO的类型
     * @param <E>    结果集
     * @param <T>    VO
     * @return 返回查询结果，将此对象转换成json，可被datagrid识别
     */
    public static <E, T> PageEasyui<T> queryForEasyuiDatagrid(SchMapper<E, ?> mapper, Query query, Class<T> clazz) {
        PageEasyui pageInfo = queryForEasyuiDatagrid(mapper, query);
        PageEasyui<T> ret = new PageEasyui<>();
        List list = pageInfo.getRows();
        if (CollectionUtils.isNotEmpty(list)) {
            List<T> retList = new ArrayList<>(list.size());
            try {
                for (Object object : list) {
                    if (clazz == object.getClass()) {
                        retList.add((T) object);
                    } else {
                        T t = clazz.newInstance();
                        BeanUtils.copyProperties(object, t);
                        retList.add(t);
                    }
                }
                BeanUtils.copyProperties(pageInfo, ret);
                ret.setList(retList);
            } catch (Exception e) {
                throw new QueryException(e);
            }
        } else {
            ret.setList(Collections.emptyList());
        }
        return ret;
    }

    /**
     * 为easyui表格提供的查询
     *
     * @param mapper mapper
     * @param query  查询对象
     * @param <E>    结果集对象
     * @return 返回查询结果，将此对象转换成json，可被datagrid识别
     */
    public static <E> PageEasyui<E> queryForEasyuiDatagrid(SchMapper<E, ?> mapper, Query query) {
        return query(mapper, query, PageEasyui.class);
    }
}