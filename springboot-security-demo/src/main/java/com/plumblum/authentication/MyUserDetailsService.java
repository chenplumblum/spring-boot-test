package com.plumblum.authentication;

import com.plumblum.entity.Permission;
import com.plumblum.entity.Role;
import com.plumblum.entity.User;
import com.plumblum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Auther: cpb
 * @Date: 2019/1/9 16:46
 * @Description:
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    /**
     * 授权的时候是对角色授权，而认证的时候应该基于资源，而不是角色，因为资源是不变的，而用户的角色是会变的
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByName(username);
        if (null == user) {
            throw new UsernameNotFoundException(username);
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //获取角色和权限set
        List<String> roleList= userService.findRoles(user.getUsername());
        List<String> permissionList= userService.findPermissions(user.getUsername());
//        Set<String> role = new HashSet<>(roleList);
//        Set<String> permission = new HashSet<>(permissionList.size());
//        role.addAll(roleList);
//        permission.addAll(permissionList);
        for(String permissionName : permissionList){
            authorities.add(new SimpleGrantedAuthority(permissionName));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
