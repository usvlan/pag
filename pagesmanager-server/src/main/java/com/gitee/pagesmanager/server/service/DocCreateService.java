package com.gitee.pagesmanager.server.service;

import com.gitee.easyopen.bean.Consts;
import com.gitee.fastmybatis.core.query.Query;
import com.gitee.fastmybatis.core.query.Sort;
import com.gitee.fastmybatis.core.util.MyBeanUtil;
import com.gitee.pagesmanager.server.common.DocBean;
import com.gitee.pagesmanager.server.common.ReleaseContext;
import com.gitee.pagesmanager.server.entity.Doc;
import com.gitee.pagesmanager.server.entity.DocContent;
import com.gitee.pagesmanager.server.entity.Project;
import com.gitee.pagesmanager.server.mapper.DocContentMapper;
import com.gitee.pagesmanager.server.mapper.DocMapper;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author tanghc
 */
@Service
public class DocCreateService {

    Logger logger = LoggerFactory.getLogger(getClass());

    private static final String SIDEBAR_FILENAME = "docs/_sidebar.md";

    private static final String MODULE_TPL = "\r\n\r\n* %s\r\n\r\n";
    // files/hello.md
    private static final String FILE_NAME_TPL = "files/%s.md";
    //  *[hello](files/hello.md)
    private static final String FILE_TPL = "  * [%s](%s)\r\n";

    @Autowired
    DocMapper docMapper;
    @Autowired
    DocContentMapper docContentMapper;

    /**
     * 生成markdown文档
     * @param releaseContext
     * @throws IOException
     */
    public void createMarkdownDoc(ReleaseContext releaseContext) throws IOException {
        List<DocBean> docList = this.listAllDoc(releaseContext.getId());
        List<DocBean> docTree = buildTreeData(docList);
        String destPath = releaseContext.getDestPath();
        logger.info("生成markdown文档文件，保存路径：{}", destPath);
        
        this.createSidebar(docTree, releaseContext);
        
        for (DocBean docBean : docTree) {
            List<DocBean> children = docBean.getChildren();
            for (DocBean item : children) {
                this.createDocFile(item, destPath);
            }
        }
    }

    /**
     * 生成侧边菜单
     *
     * @param docTree
     */
    protected void createSidebar(Collection<DocBean> docTree, ReleaseContext releaseContext) throws IOException {
        StringBuilder sidebarContent = new StringBuilder();
        for (DocBean docBean : docTree) {
            // * Getting started 一级
            sidebarContent.append(String.format(MODULE_TPL, docBean.getName()));
            List<DocBean> children = docBean.getChildren();
            for (DocBean item : children) {
                // * [Quick start](quickstart.md) 二级
                String filename = String.format(FILE_NAME_TPL, item.getName());
                sidebarContent.append(String.format(FILE_TPL, item.getName(), filename));
            }
        }
        String siderbarFilePath = releaseContext.getDestPath() + File.separator +  SIDEBAR_FILENAME;
        FileUtils.write(new File(siderbarFilePath), sidebarContent.toString(), Consts.UTF8);
    }

    protected void createDocFile(DocBean item, String destPath) throws IOException {
        // /User/aa/docmanager-dest/files/hello.md
        String filepath = destPath + File.separator + "docs" + File.separator + String.format(FILE_NAME_TPL, item.getName());
        String fileContent = item.getContent();
        FileUtils.write(new File(filepath), fileContent, Consts.UTF8);
    }

    private List<DocBean> listAllDoc(int projectId) {
        Query query = new Query();
        query.eq("project_id", projectId)
                .eq("is_show", 1)
                .orderby("order_index", Sort.DESC).orderby("id", Sort.ASC);

        List<Doc> docList = docMapper.list(query);
        List<DocBean> ret = new ArrayList<>(docList.size());
        for (Doc doc : docList) {
            DocBean docBean = new DocBean();
            MyBeanUtil.copyProperties(doc, docBean);
            if(doc.getParentId() != 0) {
                DocContent docContent = docContentMapper.getByColumn("doc_id", doc.getId());
                docBean.setContent(docContent.getContent());
            }
            ret.add(docBean);
        }
        return ret;
    }

    /**
     * 构建树形菜单
     * @param list
     * @return
     */
    public static List<DocBean> buildTreeData(List<DocBean> list) {
        List<DocBean> menu = new ArrayList<DocBean>();
        resolveMenuTree(list, 0, menu);
        return menu;
    }

    public static int resolveMenuTree(List<DocBean> menus, int parentMenuId,
                                      List<DocBean> nodes) {

        int count = 0;
        for (DocBean menu : menus) {
            if (menu.getParentId() == parentMenuId) {
                DocBean node = new DocBean();

                nodes.add(node);
                node.setName(menu.getName());
                node.setContent(menu.getContent());
                node.setParentId(menu.getParentId());
                node.setChildren(new ArrayList<DocBean>());

                resolveMenuTree(menus, menu.getId(), node.getChildren());
                count++;
            }
        }
        return count;
    }


}
