package com.gitee.pagesmanager.server.api.param;

import com.gitee.easyopen.doc.annotation.ApiDocField;

import javax.validation.constraints.NotNull;

/**
 * @author tanghc
 */
public class IdParam {

    @ApiDocField(description = "id", required = true, example = "1")
    @NotNull(message = "id不能为空")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
