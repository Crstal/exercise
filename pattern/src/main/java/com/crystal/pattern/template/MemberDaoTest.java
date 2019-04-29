package com.crystal.pattern.template;

import com.crystal.pattern.template.dao.MemberDao;

import java.sql.SQLException;

public class MemberDaoTest {
    public static void main(String[] args) throws SQLException {
        MemberDao memberDao = new MemberDao();
        memberDao.query();
    }
}
