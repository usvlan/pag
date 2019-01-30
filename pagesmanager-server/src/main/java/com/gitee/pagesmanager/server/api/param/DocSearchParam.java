package com.gitee.pagesmanager.server.api.param;

import com.gitee.easyopen.doc.annotation.ApiDocField;
import com.gitee.fastmybatis.core.support.EasyuiDatagridParam;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author tanghc
 */
@Data
public class DocSearchParam extends EasyuiDatagridParam {
    @ApiDocField(description = "项目id", required = true)
    @NotNull(message = "项目id不能为空")
    private Integer projectId;
}
