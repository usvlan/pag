package com.gitee.pagesmanager.server.api.param;

import com.gitee.easyopen.doc.annotation.ApiDocField;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DocCreateParam {
    @NotBlank(message = "文档名称不能空")
    @Length(min = 1, max = 50, message = "文档名称长度为1~50")
    @ApiDocField(description = "文档名称", required = true, example = "文档1")
    private String name;

    @NotNull(message = "所属项目不能空")
    @ApiDocField(description = "所属项目", required = true, example = "0")
    private Integer projectId;

    @ApiDocField(description = "文档内容", required = true, example = "内容。。")
    private String content;

    @ApiDocField(description = "排序索引", example = "100000")
    private Integer orderIndex;

    private Integer parentId;

    @ApiDocField(description = "是否显示")
    private Integer isShow = 1;


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }
}
