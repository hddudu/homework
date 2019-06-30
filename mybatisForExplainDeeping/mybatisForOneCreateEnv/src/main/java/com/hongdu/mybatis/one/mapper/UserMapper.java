package com.hongdu.mybatis.one.mapper;

import com.hongdu.mybatis.one.entity.User;

import java.util.Map;

/**
 * @ClassName UserMapper
 * @Description mapper接口 匹配mapper文件的sql
 * @Author dudu
 * @Date 2019/6/29 23:45
 * @Version 1.0
 */
public interface UserMapper {

    /**
     * get 获取单个对象
     * list 获取多个对象
     * count 计算
     * save/insert 新增
     * remove/delete 删除
     * update 更新
     * @param map
     * @return
     */
    User getUser(Map<String, Integer> map);
}
