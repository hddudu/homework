package com.hongdu.spring.demo.mvc;

import com.hongdu.spring.annotation.HdAutowired;
import com.hongdu.spring.annotation.HdController;
import com.hongdu.spring.annotation.HdRequestMapping;
import com.hongdu.spring.annotation.HdRequestParam;
import com.hongdu.spring.demo.service.IModifyService;
import com.hongdu.spring.demo.service.IQueryService;
import com.hongdu.spring.mvc.servlet.HdModelAndViewV1;

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
	//http://localhost:8182/idm/web/query.json?name=hongdu
	@HdRequestMapping("/query.json")
	public HdModelAndViewV1 query(HttpServletRequest request, HttpServletResponse response,
								  @HdRequestParam("name") String name){
		String result = queryService.query(name);
		return out(response,result);
	}
	//http://localhost:8182/idm/web/query.json?name=hongdu
	@HdRequestMapping("/add*json")
	public HdModelAndViewV1 add(HttpServletRequest request,HttpServletResponse response,
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
			return new HdModelAndViewV1("500",model);
		}

	}
	
	@HdRequestMapping("/remove.json")
	public HdModelAndViewV1 remove(HttpServletRequest request,HttpServletResponse response,
		   @HdRequestParam("id") Integer id){
		String result = modifyService.remove(id);
		return out(response,result);
	}
	
	@HdRequestMapping("/edit.json")
	public HdModelAndViewV1 edit(HttpServletRequest request,HttpServletResponse response,
			@HdRequestParam("id") Integer id,
			@HdRequestParam("name") String name){
		String result = modifyService.edit(id,name);
		return out(response,result);
	}
	
	
	
	private HdModelAndViewV1 out(HttpServletResponse resp,String str){
		try {
			resp.getWriter().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String toString() {
		return "MyAction{" +
				"queryService=" + queryService +
				", modifyService=" + modifyService +
				'}';
	}
}
