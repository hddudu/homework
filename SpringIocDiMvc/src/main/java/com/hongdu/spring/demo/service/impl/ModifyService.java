package com.hongdu.spring.demo.service.impl;


import com.hongdu.spring.annotation.HdService;
import com.hongdu.spring.demo.service.IModifyService;

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
		throw new Exception("这是故意抛的异常！！");
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
