package com.hongdu.gupao.template.jdbctemplate.dao;

import com.hongdu.gupao.template.jdbctemplate.JdbcTemplate;
import com.hongdu.gupao.template.jdbctemplate.Member;
import com.hongdu.gupao.template.jdbctemplate.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

/**
 * @ClassName MemberDao
 * @Description TODO
 * @Author dudu
 * @Date 2019/6/19 19:45
 * @Version 1.0
 */
public class MemberDao extends JdbcTemplate {

    public MemberDao(DataSource dataSource) {
        super(dataSource);
    }

    public List<?> listAll() {
        String sql = "select * from t_member";
        return super.executeQuery(sql, new RowMapper<Object>() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws Exception {
                Member member = new Member();
                //字段过多，原型模式
                member.setUserName(rs.getString("userName"));
                member.setPassword(rs.getString("password"));
                member.setAge(rs.getInt("age"));
                member.setAddress(rs.getString("address"));
                return member;
            }
        }, null);
    }

}
