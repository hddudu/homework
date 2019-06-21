package com.hongdu.gupao.template.jdbctemplate;

/**
 * @ClassName Member
 * @Description 实体 会员
 * @Author dudu
 * @Date 2019/6/19 19:43
 * @Version 1.0
 */
public class Member {

    private String userName;
    private String password;
    private String nickName;
    private Integer age;
    private String address;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
