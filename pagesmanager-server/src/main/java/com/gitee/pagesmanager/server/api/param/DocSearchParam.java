package com.gitee.pagesmanager.server.api.param;

import com.gitee.easyopen.doc.annotation.ApiDocField;
import com.gitee.fastmybatis.core.support.EasyuiDatagridParam;

import javax.validation.constraints.NotNull;

/**
 * @author tanghc
 */
public class DocSearchParam extends EasyuiDatagridParam {
    @ApiDocField(description = "项目id", required = true)
    @NotNull(message = "项目id不能为空")
    private Integer projectId;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
