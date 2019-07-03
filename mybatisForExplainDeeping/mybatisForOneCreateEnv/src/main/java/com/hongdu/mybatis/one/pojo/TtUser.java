package com.hongdu.mybatis.one.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName TtUser
 * @Description 用户实体： 可能下多个订单 ； 一对多关系
 * @Author dudu
 * @Date 2019/7/2 14:06
 * @Version 1.0
 */
@Data
public class TtUser {

    /**
     * 用户全部字段属性
     */
    private int id;
    private String userName;
    private int sex;
    private Date birthDay;
    private String address;

    /**
     * 用户在别的表的关联属性：
     *  collections
     */
    private List<TtOrder> ttOrders;





}
