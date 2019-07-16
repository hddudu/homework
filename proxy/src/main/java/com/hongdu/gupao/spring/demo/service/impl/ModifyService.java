package com.hongdu.gupao.spring.demo.service.impl;


import com.hongdu.gupao.spring.demo.service.IModifyService;
import com.hongdu.gupao.spring.spring.annotation.HdService;

/**
 * 增删改业务
 * @author Tom
 *
 */
@HdService
public class ModifyService implements IModifyService {

	/**
	 * 增加
	 */
	@Override
	public String add(String name,String addr) throws Exception {
		throw new Exception("这是Tom老师故意抛的异常！！");
		//return "modifyService add,name=" + name + ",addr=" + addr;
	}

	/**
	 * 修改
	 */
	@Override
	public String edit(Integer id,String name) {
		return "modifyService edit,id=" + id + ",name=" + name;
	}

	/**
	 * 删除
	 */
	@Override
	public String remove(Integer id) {
		return "modifyService id=" + id;
	}
	
}
