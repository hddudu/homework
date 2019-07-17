package com.hongdu.spring.mvc.servlet;

import java.util.Map;

/**
 * @ClassName HdModelAndView
 * @Description 视图和模型
 * @Author dudu
 * @Date 2019/7/15 18:45
 * @Version 1.0
 */
public class HdModelAndViewV1 {
    /**
     * 视图名称
     */
    private String viewName;

    /**
     * 模型map
     */
    private Map<String, ?> model;

    /**
     * 返回一个视图
     * @param viewName
     */
    public HdModelAndViewV1(String viewName) {
        this.viewName = viewName;
    }

    /**
     * 返回一个 视图 携带 数据的模型的视图
     * @param viewName
     * @param model
     */
    public HdModelAndViewV1(String viewName, Map<String, ?> model) {
        this.viewName = viewName;
        this.model = model;
    }

    public String getViewName() {
        return viewName;
    }

    public Map<String, ?> getModel() {
        return model;
    }
}
