package com.gitee.pagesmanager.server.entity;

import com.gitee.fastmybatis.core.annotation.LogicDelete;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


/**
 * 表名：doc
 */
@Table(name = "doc")
public class Doc {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**  数据库字段：id */
    private Integer id;

    /**  数据库字段：name */
    private String name;

    /**  数据库字段：order_index */
    private Integer orderIndex;

    /**  数据库字段：parent_id */
    private Integer parentId;

    /**  数据库字段：project_id */
    private Integer projectId;

    /**  数据库字段：is_show */
    private Integer isShow;

    /**  数据库字段：is_deleted */
    @LogicDelete
    private Integer isDeleted;

    /**  数据库字段：gmt_create */
    private Date gmtCreate;

    /**  数据库字段：gm_update */
    private Date gmtUpdate;

    /**  数据库字段：doc.id */
    public void setId(Integer id) {
        this.id = id;
    }

    /**  数据库字段：doc.id */
    public Integer getId() {
        return this.id;
    }

    /**  数据库字段：doc.name */
    public void setName(String name) {
        this.name = name;
    }

    /**  数据库字段：doc.name */
    public String getName() {
        return this.name;
    }

    /**  数据库字段：doc.order_index */
    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    /**  数据库字段：doc.order_index */
    public Integer getOrderIndex() {
        return this.orderIndex;
    }

    /**  数据库字段：doc.parent_id */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**  数据库字段：doc.parent_id */
    public Integer getParentId() {
        return this.parentId;
    }

    /**  数据库字段：doc.project_id */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**  数据库字段：doc.project_id */
    public Integer getProjectId() {
        return this.projectId;
    }

    /**  数据库字段：doc.is_show */
    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    /**  数据库字段：doc.is_show */
    public Integer getIsShow() {
        return this.isShow;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    /**  数据库字段：doc.gmt_create */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**  数据库字段：doc.gmt_create */
    public Date getGmtCreate() {
        return this.gmtCreate;
    }

    /**  数据库字段：doc.gm_update */
    public Date getGmtUpdate() {
        return this.gmtUpdate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((id == null) ? 0 : id.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Doc other = (Doc) obj;

        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Doc [");
        sb.append("id=").append(id);
        sb.append(", ");
        sb.append("name=").append(name);
        sb.append(", ");
        sb.append("orderIndex=").append(orderIndex);
        sb.append(", ");
        sb.append("parentId=").append(parentId);
        sb.append(", ");
        sb.append("projectId=").append(projectId);
        sb.append(", ");
        sb.append("isShow=").append(isShow);
        sb.append(", ");
        sb.append("gmtCreate=").append(gmtCreate);
        sb.append(", ");
        sb.append("gmUpdate=").append(gmtUpdate);
        sb.append("]");

        return sb.toString();
    }
}
