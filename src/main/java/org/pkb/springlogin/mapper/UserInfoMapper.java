package org.pkb.springlogin.mapper;

import org.pkb.springlogin.model.UserInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfoMapper implements RowMapper<UserInfo> {

    public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

        String userName = rs.getString("Username");
        String password = rs.getString("Password");
        int enabled = rs.getInt("Enabled");

        return new UserInfo(userName, password, enabled);
    }

}