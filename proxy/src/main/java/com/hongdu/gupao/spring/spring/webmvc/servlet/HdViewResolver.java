package com.hongdu.gupao.spring.spring.webmvc.servlet;

import java.io.File;
import java.util.Locale;

/**
 * @ClassName HdViewResolver
 * @Description 视图解析器
 * @Author dudu
 * @Date 2019/7/15 18:36
 * @Version 1.0
 */
public class HdViewResolver {

    private final String DEFAULT_TEMPLATE__SUFFIX = ".html";

    /**
     * 文件 根路径
     */
    private File templateRootDir;

    /**
     * 初始化文件 或者 目录
     * @param tempDir
     */
    public HdViewResolver(String tempDir) {
        String templateRootPath = this.getClass().getClassLoader().getResource(tempDir).getFile();
        templateRootDir = new File(templateRootPath);
    }

    public HdView resolveViewName(String viewName, Locale locale) {
        if(null == viewName || "".equals(viewName.trim())) {
            return null;
        }
        viewName = viewName.endsWith(DEFAULT_TEMPLATE__SUFFIX) ? viewName : (viewName + DEFAULT_TEMPLATE__SUFFIX);
        File tempFile = new File((templateRootDir.getPath() + "/" + viewName).replaceAll("/+", "/"));
        return new HdView(tempFile);
    }

}
