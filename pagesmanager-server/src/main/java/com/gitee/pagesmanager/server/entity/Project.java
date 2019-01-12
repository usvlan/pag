package com.gitee.pagesmanager.server.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


/**
 * 表名：project
 */
@Table(name = "project")
public class Project {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**  数据库字段：id */
    private Integer id;

    /**  数据库字段：name */
    private String name;

    /**  数据库字段：local_git_path */
    private String localGitPath;

    /**  数据库字段：git_cmd */
    private String gitCmd;

    /**  数据库字段：git_url */
    private String gitUrl;

    /**  数据库字段：gmt_create */
    private Date gmtCreate;

    /**  数据库字段：gmt_update */
    private Date gmtUpdate;

    /**  数据库字段：project.id */
    public void setId(Integer id) {
        this.id = id;
    }

    /**  数据库字段：project.id */
    public Integer getId() {
        return this.id;
    }

    /**  数据库字段：project.name */
    public void setName(String name) {
        this.name = name;
    }

    /**  数据库字段：project.name */
    public String getName() {
        return this.name;
    }

    /**  数据库字段：project.local_git_path */
    public void setLocalGitPath(String localGitPath) {
        this.localGitPath = localGitPath;
    }

    /**  数据库字段：project.local_git_path */
    public String getLocalGitPath() {
        return this.localGitPath;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    /**  数据库字段：project.git_cmd */
    public void setGitCmd(String gitCmd) {
        this.gitCmd = gitCmd;
    }

    /**  数据库字段：project.git_cmd */
    public String getGitCmd() {
        return this.gitCmd;
    }

    /**  数据库字段：project.gmt_create */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**  数据库字段：project.gmt_create */
    public Date getGmtCreate() {
        return this.gmtCreate;
    }

    /**  数据库字段：project.gmt_update */
    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    /**  数据库字段：project.gmt_update */
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

        Project other = (Project) obj;

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
        sb.append("Project [");
        sb.append("id=").append(id);
        sb.append(", ");
        sb.append("name=").append(name);
        sb.append(", ");
        sb.append("localGitPath=").append(localGitPath);
        sb.append(", ");
        sb.append("gitUrl=").append(gitUrl);
        sb.append(", ");
        sb.append("gitCmd=").append(gitCmd);
        sb.append(", ");
        sb.append("gmtCreate=").append(gmtCreate);
        sb.append(", ");
        sb.append("gmtUpdate=").append(gmtUpdate);
        sb.append("]");

        return sb.toString();
    }
}
