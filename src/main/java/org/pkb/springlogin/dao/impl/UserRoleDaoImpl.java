package org.pkb.springlogin.dao.impl;


import org.pkb.springlogin.dao.UserRoleDao;
import org.pkb.springlogin.model.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;


/**
 * Created by Rakshith on 8/1/17.
 */
@Service
@Transactional
@Repository
public class UserRoleDaoImpl extends JdbcDaoSupport implements UserRoleDao {

    @Autowired
    public UserRoleDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    @Override
    public void insertIntoUserRoles(UserRoles userRoles) {
        String sql = "INSERT INTO User_Roles (username, user_role) VALUES (?,?)";
        this.getJdbcTemplate().update(sql,
                userRoles.getUserName(), userRoles.getUserRole());
    }

    @Override
    public List<String> getUserRoles(String userName) {
        String sql = "Select r.User_Role "//
                + " from User_Roles r where r.Username = ? ";

        Object[] params = new Object[]{userName};

        List<String> roles = this.getJdbcTemplate().queryForList(sql, params, String.class);

        return roles;
    }


}
