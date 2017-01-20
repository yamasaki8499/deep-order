package com.deepbar.dao;

import com.deepbar.entity.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by rayl on 2017/1/20.
 */
@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public List<TUser> findAll() {
        return jdbcTemplate.query("select * from t_user", new UserRowMapper());
    }


    private class UserRowMapper implements RowMapper<TUser> {
        @Override
        public TUser mapRow(ResultSet resultSet, int i) throws SQLException {
            return new TUser(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("email"));
        }
    }
}
