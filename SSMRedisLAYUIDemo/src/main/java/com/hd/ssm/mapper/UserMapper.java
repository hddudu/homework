package com.hd.ssm.mapper;

import com.hd.ssm.pojo.User;

import java.util.List;


public interface UserMapper {

    public User selectByPrimaryKey(Integer userId);

    public List<User> selectAllUser();

    public int insertUser(User user);

    public int deleteUser(int id);

    public List<User> findUsers(String keyWords);

    public int editUser(User user);

	public Integer selectUsersCount();

	public List<Integer> selectIds();
}