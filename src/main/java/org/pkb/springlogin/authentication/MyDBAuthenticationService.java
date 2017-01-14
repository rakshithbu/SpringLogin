package org.pkb.springlogin.authentication;

import org.pkb.springlogin.dao.UserInfoDAO;
import org.pkb.springlogin.dao.UserRoleDao;
import org.pkb.springlogin.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyDBAuthenticationService implements UserDetailsService {

    @Autowired
    private UserInfoDAO userInfoDAO;
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoDAO.findUserInfo(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        } else {
            if (userInfo.getEnabled() == 0) {
                throw new BadCredentialsException("Please verify your mail");
            }
        }

        // [USER,ADMIN,..]
        List<String> roles = userRoleDao.getUserRoles(username);

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roles != null) {
            for (String role : roles) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
                grantList.add(authority);
            }
        }

        UserDetails userDetails = (UserDetails) new User(userInfo.getUserName(), //
                userInfo.getPassword(), grantList);

        return userDetails;
    }

}