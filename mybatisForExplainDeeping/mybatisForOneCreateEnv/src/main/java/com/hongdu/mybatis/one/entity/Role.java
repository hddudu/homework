package com.hongdu.mybatis.one.entity;

/**
 * @ClassName Role
 * @Description 角色实体
 * @Author dudu
 * @Date 2019/6/30 0:00
 * @Version 1.0
 */
public class Role {

    private Integer id;
    private String roleName;
    private String roleDesc;
    private String rolePrivLevel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public String getRolePrivLevel() {
        return rolePrivLevel;
    }

    public void setRolePrivLevel(String rolePrivLevel) {
        this.rolePrivLevel = rolePrivLevel;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                ", rolePrivLevel='" + rolePrivLevel + '\'' +
                '}';
    }
}
