package com.gitee.pagesmanager.server.service;

import com.gitee.fastmybatis.core.util.MyBeanUtil;
import com.gitee.pagesmanager.server.common.CmdUtil;
import com.gitee.pagesmanager.server.common.ReleaseContext;
import com.gitee.pagesmanager.server.entity.Project;
import com.gitee.pagesmanager.server.message.CommonErrors;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * 发布业务类
 * @author tanghc
 */
@Service
public class ReleaseService {
    Logger logger = LoggerFactory.getLogger(getClass());

    static final String DEST_FOLDER = "pagesmanager-dest";
    static final String SCRIPT_SH = "push.sh";
    static final String SCRIPT_BAT = "push.bat";

    static boolean isWin;

    static {
        String os = System.getProperty("os.name");
        // 如果是win操作系统
        isWin = os.toLowerCase().startsWith("win");
    }

    @Autowired
    DocCreateService docCreateService;

    /**
     * 发布文档
     * @param project
     */
    public void release(Project project) {
        ReleaseContext releaseContext = this.buildReleaseContext(project);
        try {
            this.createDocContent(releaseContext);
            this.runCmd(releaseContext);
        } catch (Exception e) {
            logger.error("文档发布失败，releaseContext：{}", releaseContext.toString(), e);
            throw CommonErrors.RELEASE_ERROR.getException();
        }
    }
    
    private ReleaseContext buildReleaseContext(Project project) {
        ReleaseContext releaseContext = new ReleaseContext();
        String destPath = buildDestPath(project);
        MyBeanUtil.copyProperties(project, releaseContext);
        releaseContext.setDestPath(destPath);
        return releaseContext;
    }

    public static String buildDestPath(Project project) {
        return project.getLocalGitPath() + File.separator + DEST_FOLDER;
    }

    /**
     * 拷贝模板
     * @param project
     */
    public void copyDocTemplate(Project project) throws IOException {
        String destPath = buildDestPath(project);
        // 先删除老的模板
        File destDir = new File(destPath);
        if(!destDir.exists()) {
            ClassPathResource destRes = new ClassPathResource(DEST_FOLDER);
            FileUtils.copyDirectory(destRes.getFile(), new File(destPath));
        }
        this.copyScriptFileToWorkspace(project.getLocalGitPath());
    }

    private void copyScriptFileToWorkspace(String localGitPath) throws IOException {
        // 拷贝脚本文件到项目根目录
        ClassPathResource scriptRes = new ClassPathResource(SCRIPT_SH);
        // /Users/xxx/projecta/push.sh
        File scriptFile = new File(localGitPath + File.separator + SCRIPT_SH);
        FileUtils.copyFile(scriptRes.getFile(), scriptFile);

        scriptRes = new ClassPathResource(SCRIPT_BAT);
        scriptFile = new File(localGitPath + File.separator + SCRIPT_BAT);
        FileUtils.copyFile(scriptRes.getFile(), scriptFile);
    }


    /**
     * 生成文档到模板
     * @param releaseContext
     */
    private void createDocContent(ReleaseContext releaseContext) throws IOException {
        docCreateService.createMarkdownDoc(releaseContext);
    }

    /**
     * 运行命令
     * @param releaseContext
     */
    private void runCmd(ReleaseContext releaseContext) {
        String localGitPath = releaseContext.getLocalGitPath();
        String out;
        // 如果是win操作系统
        if(isWin){
            // /Users/xxx/aaa/push.sh
            String shellFilePath = localGitPath + File.separator + SCRIPT_BAT;
            // 执行完不关闭对话框
            String cmd = "cmd.exe /k "+  shellFilePath;
            // 执行完关闭对话框
//            String cmd = "cmd.exe /c " + shellFilePath;
            out = CmdUtil.runCmd(cmd, localGitPath);
        } else {
            // 设置执行权限
            CmdUtil.runCmd("chmod +x " + SCRIPT_SH, localGitPath);
            out = CmdUtil.runCmd("sh " + SCRIPT_SH, localGitPath);
        }
        logger.info("cmd执行结果:\n{}", out);
    }



}
