package com.hongdu.mybatis.one.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName TtOrder
 * @Description 订单对用户 ： 一对一关系
 * @Author dudu
 * @Date 2019/7/2 14:07
 * @Version 1.0
 */
@Data
public class TtOrder {

    /**
     * 实体字段一个不少
     */
    private int id;
    private Integer userId;
    private String number;
    private Date createTime;
    private String note;

    /**
     * 关联属性 ： 关联查询 ：关联起了用户
     * association
     */
    private TtUser ttUser;



}
