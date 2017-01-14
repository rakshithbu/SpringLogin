package org.pkb.springlogin.dao;

import org.pkb.springlogin.model.UserInfo;

import java.util.List;

public interface UserInfoDAO {

    UserInfo findUserInfo(String userName);

    void insertIntoUsers(UserInfo userInfo);

    List<String> getUserNames();

    void enableUser(String userName);
}