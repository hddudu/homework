package com.hongdu.gupao.spring.spring.webmvc.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName HdView
 * @Description 视图解析 接口 这里直接写成了固定的类
 * @Author dudu
 * @Date 2019/7/15 17:00
 * @Version 1.0
 */
public class HdView {
    //默认内容类型
    private final String DEFAULT_CONTENT_TYPE = "text/html;charset=utf-8";

    Pattern pattern = Pattern.compile("￥\\{[^\\}]+\\}", Pattern.CASE_INSENSITIVE);

    /**
     * 需要解析的视图文件
     */
    private File viewFile;

    public HdView(File viewFile) {
        this.viewFile = viewFile;
    }

    /**
     * 渲染的方法
     */
    public void render(Map<String,?> model,
                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        StringBuffer sb = new StringBuffer();

        /**
         * 设置 模式 ： 读模式
         */
        RandomAccessFile ra = new RandomAccessFile(this.viewFile, "r");

        String line = null;
        while (null != (line = ra.readLine())) {
            line = new String(line.getBytes("ISO-8859-1"), "UTF-8");
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                String paramName = matcher.group();
                paramName = paramName.replaceAll("￥\\{|\\}","");
                Object paramValue = model.get(paramName);
                if(null == paramValue) {continue;}
                line = matcher.replaceFirst(makeStringForRegex(paramValue.toString()));
                matcher = pattern.matcher(line);
            }
            sb.append(line);
        }
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(sb.toString());
    }

    //处理特殊字符
    private String makeStringForRegex(String toString) {
        return toString.replace("\\", "\\\\").replace("*", "\\*")
                .replace("+", "\\+").replace("|", "\\|")
                .replace("{", "\\{").replace("}", "\\}")
                .replace("(", "\\(").replace(")", "\\)")
                .replace("^", "\\^").replace("$", "\\$")
                .replace("[", "\\[").replace("]", "\\]")
                .replace("?", "\\?").replace(",", "\\,")
                .replace(".", "\\.").replace("&", "\\&");
    }
}
