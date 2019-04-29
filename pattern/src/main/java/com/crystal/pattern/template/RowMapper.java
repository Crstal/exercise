package com.crystal.pattern.template;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {

    /**
     * 解析结果
     * @param rs
     * @return
     * @throws SQLException
     */
    public T mapRow(ResultSet rs) throws SQLException;
}
