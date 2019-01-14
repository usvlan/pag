package com.gitee.pagesmanager.server.api.param;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author tanghc
 */
public class PropertyGridParam {
    @NotNull(message = "field不能为空")
    private Integer id;

    @NotBlank(message = "field不能为空")
    private String field;

    @NotBlank(message = "值不能为空")
    private String value;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
