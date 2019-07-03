package com.hongdu.mybatis.ormchongxie.entity;

import lombok.Data;

/**
 * @ClassName Role
 * @Description
 * @Author dudu
 * @Date 2019/7/1 18:14
 * @Version 1.0
 */
@Data
public class Role {

    private String id;
    private  String roleName;
    private String roleDesc;

    public Role(String id, String roleName, String roleDesc) {
        this.id = id;
        this.roleName = roleName;
        this.roleDesc = roleDesc;
    }
}
