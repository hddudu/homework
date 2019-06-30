package com.hongdu.mybatis.one.mapper;

import com.hongdu.mybatis.one.entity.Role;

import java.util.Map;

/**
 * @ClassName RoleMapper
 * @Description 角色接口
 * @Author dudu
 * @Date 2019/6/30 0:00
 * @Version 1.0
 */
public interface RoleMapper {

    /**
     * 查询角色
     * @param map
     * @return
     */
    Role getRole(Map<String,Integer> map);
}
