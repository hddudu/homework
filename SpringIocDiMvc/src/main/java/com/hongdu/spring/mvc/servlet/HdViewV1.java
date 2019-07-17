package com.hongdu.spring.mvc.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName HdViewV1
 * @Description 视图渲染器
 * @Author dudu
 * @Date 2019/7/17 11:08
 * @Version 1.0
 */
public class HdViewV1 {

    public final String DEFAULT_CONTENT_TYPE = "text/html;charse=UTF-8";

    private File file;

    public HdViewV1(File file) {
        this.file = file;
    }

    /**
     * 渲染成了 html 能够识别的字符串
     * @param model
     * @param request
     * @param response
     * @throws Exception
     */
    public void render( Map<String, ?> model,
                       HttpServletRequest request, HttpServletResponse response) throws Exception {

        StringBuffer sb = new StringBuffer();

        RandomAccessFile randomAccessFile = new RandomAccessFile(this.file, "r");
        String line;
        while ((null != (line = randomAccessFile.readLine()))) {
            line = new String(line.getBytes("ISO-8859-1"), "UTF-8");
            //只要中间没有出现结束符号，就认为是整一个字符串
            String regex = "￥\\{[^\\}]+\\}";
            //逐个匹配
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {

                String paramName = matcher.group();
                paramName = paramName.replaceAll("￥\\{|\\}", "");
                Object paramValue = model.get(paramName);
                if(null == paramValue) {
                    continue;
                }
                //替换
                line = matcher.replaceFirst(makeStringForRegex(paramValue.toString()));
                //更新 : 继续匹配
                matcher = pattern.matcher(line);
            }
            sb.append(line);
        }


        response.setCharacterEncoding("UTF-8");
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
