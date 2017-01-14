package org.pkb.springlogin.dao.impl;

import org.pkb.springlogin.dao.UserInfoDAO;
import org.pkb.springlogin.mapper.UserInfoMapper;
import org.pkb.springlogin.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Transactional
@Repository
public class UserInfoDAOImpl extends JdbcDaoSupport implements UserInfoDAO {

    @Autowired
    public UserInfoDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public UserInfo findUserInfo(String userName) {
        String sql = "Select u.Username,u.Password,u.Enabled  from Users u where u.Username = ? ";

        Object[] params = new Object[]{userName};
        UserInfoMapper mapper = new UserInfoMapper();
        try {
            ;
            UserInfo userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return userInfo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void insertIntoUsers(UserInfo userInfo) {
        String sql = "INSERT INTO Users (username, email_id,dob, password, enabled) VALUES (?,?,?,?,?)";
        this.getJdbcTemplate().update(sql,
                userInfo.getUserName(), userInfo.getEmail(), userInfo.getDob(), userInfo.getPassword(), 0);
    }

    public List<String> getUserNames() {
        String sql = "SELECT username FROM Users";
        List<String> userName = this.getJdbcTemplate().queryForList(sql, String.class);
        return userName;
    }


    public void enableUser(String userName) {
        String sql = "UPDATE Users SET enabled = 1 WHERE username = ?";
        this.getJdbcTemplate().update(sql, userName);
    }
}