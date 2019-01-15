package com.gitee.pagesmanager.server.api;

import com.gitee.easyopen.annotation.Api;
import com.gitee.easyopen.annotation.ApiService;
import com.gitee.easyopen.doc.annotation.ApiDoc;
import com.gitee.easyopen.doc.annotation.ApiDocField;
import com.gitee.easyopen.doc.annotation.ApiDocMethod;
import com.gitee.easyopen.exception.ApiException;
import com.gitee.fastmybatis.core.query.Query;
import com.gitee.fastmybatis.core.query.Sort;
import com.gitee.fastmybatis.core.support.PageEasyui;
import com.gitee.fastmybatis.core.util.MyBeanUtil;
import com.gitee.pagesmanager.server.api.param.IdParam;
import com.gitee.pagesmanager.server.api.param.ProjectAddParam;
import com.gitee.pagesmanager.server.api.param.PropertyGridParam;
import com.gitee.pagesmanager.server.api.result.PropertygridRow;
import com.gitee.pagesmanager.server.common.FrontException;
import com.gitee.pagesmanager.server.entity.Project;
import com.gitee.pagesmanager.server.mapper.ProjectMapper;
import com.gitee.pagesmanager.server.service.ReleaseService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.sqlite.date.DateFormatUtils;

import javax.validation.constraints.Min;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tanghc
 */
@ApiService
@ApiDoc("项目")
public class ProjectApi {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ReleaseService releaseService;

    @Autowired
    private ProjectMapper projectMapper;

    /**
     * 创建项目
     *
     * @param param
     * @return
     */
    @Api(name = "project.create")
    @ApiDocMethod(description = "创建项目")
    public void create(ProjectAddParam param) {
        Project existProject = projectMapper.getByColumn("name", param.getName());
        if (existProject != null) {
            throw new ApiException("项目名" + param.getName() + "已存在");
        }
        Project project = new Project();
        MyBeanUtil.copyPropertiesIgnoreNull(param, project);
        String localGitPath = formatUrl(project.getLocalGitPath());
        project.setLocalGitPath(localGitPath);
        projectMapper.save(project);

        try {
            releaseService.copyDocTemplate(project);
        } catch (IOException e) {
            throw new FrontException("创建项目失败");
        }
    }

    private static String formatUrl(String url) {
        url = StringUtils.trimTrailingCharacter(url, '\\');
        url = StringUtils.trimTrailingCharacter(url, '/');
        return url;
    }

    /**
     * 获取所有项目
     *
     * @return
     */
    @Api(name = "project.listall")
    @ApiDocMethod(description = "获取所有项目")
    public List<Project> listAll() {
        Query query = new Query()
                .orderby("id", Sort.ASC);
        return projectMapper.list(query);
    }

    /**
     * @return
     */
    @Api(name = "project.propertygrid.detail")
    @ApiDocMethod(description = "获取项目详情(propertygrid)")
    public Object propertygrid(IdParam param) {
        Project project = projectMapper.getById(param.getId());
        // base
        String baseGroup = "基本信息";
        String gitGroup = "Git信息";
        List<PropertygridRow> list = Lists.newArrayList(
                new PropertygridRow("name", "项目名称", project.getName(), baseGroup, "text")
                , new PropertygridRow("创建时间", DateFormatUtils.format(project.getGmtCreate(), "yyyy-MM-dd HH:mm:ss"), baseGroup, null)
                , new PropertygridRow("修改时间", DateFormatUtils.format(project.getGmtUpdate(), "yyyy-MM-dd HH:mm:ss"), baseGroup, null)

                , new PropertygridRow("git_url","GitUrl", project.getGitUrl(), gitGroup, "text")
                , new PropertygridRow("local_git_path","本地Git项目路径", project.getLocalGitPath(), gitGroup, "text")
        );

        PageEasyui<PropertygridRow> pageInfo = new PageEasyui<>();
        pageInfo.setList(list);
        pageInfo.setTotal(list.size());

        return pageInfo;
    }

    @Api(name = "project.propertygrid.update")
    @ApiDocMethod(description = "修改属性表格")
    void updatePropertyGrid(PropertyGridParam param) {
        Query query = new Query();
        query.eq("id", param.getId());
        Map<String, Object> map = new HashMap<>();
        map.put(param.getField(), param.getValue());
        projectMapper.updateByMap(map, query);
    }


    @Api(name = "project.release")
    @ApiDocMethod(description = "发布文档")
    Object release(
            @ApiDocField(description = "projectId")
            @Min(value = 1, message = "错误的projectId")
            int projectId) {

        Project project = projectMapper.getById(projectId);
        if (project == null) {
            throw new ApiException("项目不存在");
        }

        releaseService.release(project);

        return "";
    }




}
