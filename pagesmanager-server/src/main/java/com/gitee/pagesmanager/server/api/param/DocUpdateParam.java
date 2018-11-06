package com.gitee.pagesmanager.server.api.param;

import com.gitee.easyopen.doc.annotation.ApiDocField;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DocUpdateParam {
    @NotNull(message = "id不能空")
    @ApiDocField(description = "文档id", required = true)
    private Integer id;

    @NotBlank(message = "资源名称不能空")
    @Length(min = 1, max = 20, message = "资源名称长度为1~20")
    @ApiDocField(description = "资源名称", required = true)
    // 资源名称
    private String name;

    @NotBlank(message = "资源内容不能空")
    @ApiDocField(description = "资源内容", required = true)
    private String content;
    
    @NotNull(message = "parentId不能空")
    @ApiDocField(description = "parentId", required = true)
    private Integer parentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

}
