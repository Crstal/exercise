package com.crystal.pattern.template.dao;

import com.crystal.pattern.template.JdbcTemplate;
import com.crystal.pattern.template.RowMapper;
import com.crystal.pattern.template.entity.Member;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MemberDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(null);

    public List<Member> query() throws SQLException {
        String sql = "select * from t_member";
        return jdbcTemplate.executeQuery(sql, null, new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs) throws SQLException {
                Member member = new Member();
                member.setId(rs.getString("id"));
                member.setUsername(rs.getString("username"));
                member.setPassword(rs.getString("password"));
                member.setNickname(rs.getString("nickname"));
                return member;
            }
        });
    }
}
