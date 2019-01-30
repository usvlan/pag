package com.gitee.pagesmanager.server.api.param;

import com.gitee.easyopen.doc.annotation.ApiDocField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author tanghc
 */
@Data
public class ProjectUpdateParam {
    @ApiDocField(description = "项目ID", required = true)
    @NotNull(message = "项目ID不能为空")
    private Integer id;

    /**  数据库字段：name */
    @ApiDocField(description = "项目名称", required = true)
    @NotBlank(message = "项目名称不能为空")
    private String name;

    /**  数据库字段：local_git_path */
    @ApiDocField(description = "本地存放路径", required = true)
    @NotBlank(message = "本地存放路径不能为空")
    private String localGitPath;

    /**  数据库字段：git_cmd */
    private String gitCmd;

    /**  数据库字段：git_url */
    @ApiDocField(description = "Git链接", required = true)
    @NotBlank(message = "Git链接不能为空")
    private String gitUrl;
}
