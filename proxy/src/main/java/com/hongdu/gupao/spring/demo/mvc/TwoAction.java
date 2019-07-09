package com.hongdu.gupao.spring.demo.mvc;

import com.hongdu.gupao.spring.demo.service.IDemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName TwoAction
 * @Description
 * @Author dudu
 * @Date 2019/7/5 19:48
 * @Version 1.0
 */
public class TwoAction {

    private IDemoService demoService;

    public void edit(HttpServletRequest req, HttpServletResponse resp,
                     String name) {
        String result = demoService.get(name);
        try {
            resp.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
