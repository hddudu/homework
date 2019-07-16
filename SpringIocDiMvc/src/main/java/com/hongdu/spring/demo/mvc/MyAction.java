package com.hongdu.spring.demo.mvc;

import com.hongdu.gupao.spring.spring.annotation.HdRequestParam;
import com.hongdu.gupao.spring.spring.webmvc.servlet.HdModelAndView;
import com.hongdu.spring.annotation.HdAutowired;
import com.hongdu.spring.annotation.HdController;
import com.hongdu.spring.annotation.HdRequestMapping;
import com.hongdu.spring.demo.service.IModifyService;
import com.hongdu.spring.demo.service.IQueryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 公布接口url
 * @author Tom
 *
 */
@HdController
@HdRequestMapping("/web")
public class MyAction {

	@HdAutowired
	IQueryService queryService;
	@HdAutowired
	IModifyService modifyService;

	@HdRequestMapping("/query.json")
	public HdModelAndView query(HttpServletRequest request, HttpServletResponse response,
								@HdRequestParam("name") String name){
		String result = queryService.query(name);
		return out(response,result);
	}
	
	@HdRequestMapping("/add*.json")
	public HdModelAndView add(HttpServletRequest request,HttpServletResponse response,
			   @HdRequestParam("name") String name,@HdRequestParam("addr") String addr){
		String result = null;
		try {
			result = modifyService.add(name,addr);
			return out(response,result);
		} catch (Exception e) {
//			e.printStackTrace();
			Map<String,Object> model = new HashMap<String,Object>();
			model.put("detail",e.getMessage());
//			System.out.println(Arrays.toString(e.getStackTrace()).replaceAll("\\[|\\]",""));
			model.put("stackTrace", Arrays.toString(e.getStackTrace()).replaceAll("\\[|\\]",""));
			return new HdModelAndView("500",model);
		}

	}
	
	@HdRequestMapping("/remove.json")
	public HdModelAndView remove(HttpServletRequest request,HttpServletResponse response,
		   @HdRequestParam("id") Integer id){
		String result = modifyService.remove(id);
		return out(response,result);
	}
	
	@HdRequestMapping("/edit.json")
	public HdModelAndView edit(HttpServletRequest request,HttpServletResponse response,
			@HdRequestParam("id") Integer id,
			@HdRequestParam("name") String name){
		String result = modifyService.edit(id,name);
		return out(response,result);
	}
	
	
	
	private HdModelAndView out(HttpServletResponse resp,String str){
		try {
			resp.getWriter().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
