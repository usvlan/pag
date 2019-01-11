package com.gitee.pagesmanager.server.api.param;

import com.gitee.easyopen.doc.annotation.ApiDocField;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class ProjectAddParam {
    @NotBlank(message = "项目名称不能为空")
    @Length(max = 20, message = "项目名长度必须小于等于20")
    @ApiDocField(description = "项目名称", required = true, example = "第一个项目")
    // 项目名称
    private String name;

    @NotBlank(message = "本地Git项目路径不能为空")
    @Length(max = 200, message = "本地Git项目路径长度必须小于等于200")
    @ApiDocField(description = "本地Git项目路径", required = true, example = "D:/project/myproject/")
    /**  数据库字段：local_git_path */
    private String localGitPath;

    @ApiDocField(description = "git命令", example = "")
    /**  数据库字段：git_cmd */
    private String gitCmd;

    public String getLocalGitPath() {
        return localGitPath;
    }

    public void setLocalGitPath(String localGitPath) {
        this.localGitPath = localGitPath;
    }

    public String getGitCmd() {
        return gitCmd;
    }

    public void setGitCmd(String gitCmd) {
        this.gitCmd = gitCmd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}