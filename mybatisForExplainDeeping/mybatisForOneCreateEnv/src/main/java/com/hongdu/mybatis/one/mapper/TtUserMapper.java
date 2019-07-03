package com.hongdu.mybatis.one.mapper;

import com.hongdu.mybatis.one.pojo.TtUser;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TtUserMapper
 * @Description TODO
 * @Author dudu
 * @Date 2019/7/2 14:15
 * @Version 1.0
 */
public interface TtUserMapper {

    int save(TtUser ttUser);

    int update(TtUser ttUser);

    TtUser getTtUserById(int id);

    List<TtUser> listTtUsers(Map<String, String> listParams);

    int delete(int id);

    int delete(TtUser ttUser);
}
