package com.hongdu.spring.mvc.servlet;

import java.io.File;
import java.util.Locale;

/**
 * 视图 解析器 :
 *  成员
 *      文件
 *      路径
 *
 */
public class HdViewResolverV1 {

    private final String DEFAULT_TEMPLATE_SUFFIX = ".html";

    private File templateRootDir;
//    private String templateRootDir;


    public HdViewResolverV1(String templateRoot) {
        String tempRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();
        templateRootDir = new File(tempRootPath);
    }

    public HdViewV1 resolveViewName(String viewName, Locale locale) throws Exception{
        if(null == viewName || "".equals(viewName.trim())) {
            return null;
        }
        viewName = viewName.endsWith(DEFAULT_TEMPLATE_SUFFIX) ? viewName : (viewName + DEFAULT_TEMPLATE_SUFFIX);
        File templateFile = new File((templateRootDir + "/" + viewName).replaceAll("/+", "/"));
        return new HdViewV1(templateFile);
    }
}
