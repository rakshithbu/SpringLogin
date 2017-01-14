package org.pkb.springlogin.dao;

import org.pkb.springlogin.model.UserRoles;

import java.util.List;

/**
 * Created by stg0112 on 8/1/17.
 */
public interface UserRoleDao {

    List<String> getUserRoles(String userName);

    void insertIntoUserRoles(UserRoles userRoles);
}
