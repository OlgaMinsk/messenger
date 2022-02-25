package com.innowisegroup.messenger.security;

import com.innowisegroup.messenger.exception.NotFoundException;
import com.innowisegroup.messenger.model.User;
import com.innowisegroup.messenger.security.jwt.JwtUser;
import com.innowisegroup.messenger.security.jwt.JwtUserFactory;
import com.innowisegroup.messenger.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        try {
            user = userService.getByUserName(username);
        } catch (NotFoundException exception) {
            throw new UsernameNotFoundException(exception.getMessage(), exception);
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        return jwtUser;
    }
}
