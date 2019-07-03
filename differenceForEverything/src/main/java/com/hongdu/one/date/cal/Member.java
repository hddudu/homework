package com.hongdu.one.date.cal;

import java.util.Date;

/**
 * @ClassName Member
 * @Description TODO
 * @Author dudu
 * @Date 2019/7/2 10:52
 * @Version 1.0
 */
public class Member {

    private String id;//主键id

    private Integer memberType;//会员类型 ： 开通时间年限

    private String phone;//手机号码

    private String openId;//微信公众号openid

    private Date createDate;//开通时间

    private Date expireDate;//到期时间

    private Integer memberRank;//会员等级

    private String createUser;//创建用户名

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", memberType=" + memberType +
                ", phone='" + phone + '\'' +
                ", openId='" + openId + '\'' +
                ", createDate=" + createDate +
                ", expireDate=" + expireDate +
                ", memberRank=" + memberRank +
                ", createUser='" + createUser + '\'' +
                '}';
    }
}
