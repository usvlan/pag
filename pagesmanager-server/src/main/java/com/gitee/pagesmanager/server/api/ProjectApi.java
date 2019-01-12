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
import com.gitee.pagesmanager.server.api.result.PropertygridRow;
import com.gitee.pagesmanager.server.entity.Project;
import com.gitee.pagesmanager.server.mapper.ProjectMapper;
import com.gitee.pagesmanager.server.service.ReleaseService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.sqlite.date.DateFormatUtils;

import javax.validation.constraints.Min;
import java.util.List;

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
        projectMapper.save(project);
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
                .orderby("id", Sort.DESC)
                .setQueryAll(true);
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
                new PropertygridRow("项目名称", project.getName(), baseGroup, "text")
                , new PropertygridRow("创建时间", DateFormatUtils.format(project.getGmtCreate(), "yyyy-MM-dd HH:mm:ss"), baseGroup, null)
                , new PropertygridRow("修改时间", DateFormatUtils.format(project.getGmtUpdate(), "yyyy-MM-dd HH:mm:ss"), baseGroup, null)

                , new PropertygridRow("GitUrl", project.getGitUrl(), gitGroup, "text")
                , new PropertygridRow("本地Git项目路径", project.getLocalGitPath(), gitGroup, "text")
        );

        PageEasyui<PropertygridRow> pageInfo = new PageEasyui<>();
        pageInfo.setList(list);
        pageInfo.setTotal(list.size());

        return pageInfo;
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
