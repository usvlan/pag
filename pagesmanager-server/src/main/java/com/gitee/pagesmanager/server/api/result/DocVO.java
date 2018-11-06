package com.gitee.pagesmanager.server.api.result;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class DocVO {
    /**  数据库字段：id */
    private Integer id;

    /**  数据库字段：name */
    private String name;

    /**  数据库字段：order_index */
    private Integer orderIndex;

    /**  数据库字段：parent_id */
    private Integer parentId;

    /**  数据库字段：gmt_create */
    private Date gmtCreate;

    /**  数据库字段：gm_update */
    private Date gmtUpdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public Integer getParentId() {
        return parentId;
    }

    @JSONField(name = "_parentId")
    public Integer getParentId2() {
        if (parentId == 0) {
            return null;
        }
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    @Override
    public String toString() {
        return "DocVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", orderIndex=" + orderIndex +
                ", parentId=" + parentId +
                ", gmtCreate=" + gmtCreate +
                ", gmtUpdate=" + gmtUpdate +
                '}';
    }
}
