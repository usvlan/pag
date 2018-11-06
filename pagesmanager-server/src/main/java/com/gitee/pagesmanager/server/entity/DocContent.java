package com.gitee.pagesmanager.server.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


/**
 * 表名：doc_content
 */
@Table(name = "doc_content")
public class DocContent {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**  数据库字段：id */
    private Integer id;

    /**  数据库字段：doc_id */
    private Integer docId;

    /**  数据库字段：content */
    private String content;

    /**  数据库字段：gmt_create */
    private Date gmtCreate;

    /**  数据库字段：gmt_update */
    private Date gmtUpdate;

    /**  数据库字段：doc_content.id */
    public void setId(Integer id) {
        this.id = id;
    }

    /**  数据库字段：doc_content.id */
    public Integer getId() {
        return this.id;
    }

    /**  数据库字段：doc_content.doc_id */
    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    /**  数据库字段：doc_content.doc_id */
    public Integer getDocId() {
        return this.docId;
    }

    /**  数据库字段：doc_content.content */
    public void setContent(String content) {
        this.content = content;
    }

    /**  数据库字段：doc_content.content */
    public String getContent() {
        return this.content;
    }

    /**  数据库字段：doc_content.gmt_create */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**  数据库字段：doc_content.gmt_create */
    public Date getGmtCreate() {
        return this.gmtCreate;
    }

    /**  数据库字段：doc_content.gmt_update */
    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    /**  数据库字段：doc_content.gmt_update */
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

        DocContent other = (DocContent) obj;

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
        sb.append("DocContent [");
        sb.append("id=").append(id);
        sb.append(", ");
        sb.append("docId=").append(docId);
        sb.append(", ");
        sb.append("content=").append(content);
        sb.append(", ");
        sb.append("gmtCreate=").append(gmtCreate);
        sb.append(", ");
        sb.append("gmtUpdate=").append(gmtUpdate);
        sb.append("]");

        return sb.toString();
    }
}
