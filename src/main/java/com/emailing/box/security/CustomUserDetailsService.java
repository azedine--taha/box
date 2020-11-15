package com.emailing.box.security;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.emailing.box.business.User.IUserService;
import com.emailing.box.entities.Role;
import com.emailing.box.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        if (usernameOrEmail.trim().isEmpty()) {
            throw new UsernameNotFoundException("username is empty");
        }

        User user = userService.findByEmail(usernameOrEmail);

        if (user == null) {
            throw new UsernameNotFoundException("User " + usernameOrEmail + " not found");
        }

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        Set<Role> role = user.getRoles();
        role.stream()
                .map(r->{
                    return authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
                }).collect(Collectors.toList());
        return authorities;
    }

}
