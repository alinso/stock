package com.alinso.stock.security;

import com.alinso.stock.dao.UserDAO;
import com.alinso.stock.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("SecurityUserService")
public class SecurityUserService implements UserDetailsService{

    @Autowired
    private UserDAO userDAO;

    /*@Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.getByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("Username not found");
        }

       org.springframework.security.core.userdetails.User ud = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true,true, true, true, getGrantedAuthorities(user));

       CustomUserDetails CuserDetails= new CustomUserDetails(ud);
       return CuserDetails;
    }*/

    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.getByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("Username not found");
        }
        return new CustomAuthUser(user.getUsername(), user.getPassword(),
                true,true, true, true, getGrantedAuthorities(user),
                user.getId());
    }




    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));


        return authorities;
    }

}