package com.crystal.pattern.template;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Caoyue
 * @Description: 抽象模板父类
 * @Date: 2018/05/26 15:44
 */
public class JdbcTemplate {

    private DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private PreparedStatement getPreparedStatment(Connection connection, String sql, Object values) throws SQLException {
        return connection.prepareStatement(sql);
    }

    private ResultSet executeQuery(PreparedStatement stmt) throws SQLException {
        return stmt.executeQuery();
    }

    private <T> List<T> resolveResultSet(ResultSet resultSet, RowMapper<T> rowMapper) throws SQLException {
        List<T> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(rowMapper.mapRow(resultSet));
        }
        return result;
    }

    private void closeResultSet(ResultSet resultSet) throws SQLException {
        resultSet.close();
    }

    private void closeStatement(Statement stmt) throws SQLException {
        stmt.close();
    }

    private void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }

    public <T> List<T> executeQuery(String sql, Object[] values, RowMapper<T> rowMapper) throws SQLException {
        // 1.获取连接
        Connection connection = this.getConnection();
        // 2.创建语句集
        PreparedStatement stmt = this.getPreparedStatment(connection, sql, values);
        // 3.执行语句集并获得结果集
        ResultSet resultSet = this.executeQuery(stmt);
        // 4.解析语句集
        List<T> result = this.resolveResultSet(resultSet, rowMapper);
        // 5.关闭结果集
        this.closeResultSet(resultSet);
        // 6.关闭语句集
        this.closeStatement(stmt);
        // 7.关闭连接
        this.closeConnection(connection);
        return result;
    }

//    protected abstract Object processResult(ResultSet rs, int index) throws SQLException;
}
