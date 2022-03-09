package com.innowisegroup.messenger.service;

import com.innowisegroup.messenger.dto.request.AuthenticationRequest;
import com.innowisegroup.messenger.dto.response.AuthenticationResponse;
import com.innowisegroup.messenger.exception.NotFoundException;
import com.innowisegroup.messenger.model.Role;
import com.innowisegroup.messenger.model.User;
import com.innowisegroup.messenger.security.MyPasswordEncoder;
import com.innowisegroup.messenger.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    private final JwtTokenProvider jwtTokenProvider;
    private final MyPasswordEncoder passwordEncoder;
    private final UserService userService;

    @Autowired
    public AuthenticationServiceImpl(JwtTokenProvider jwtTokenProvider, MyPasswordEncoder passwordEncoder, UserService userService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest requestDto) {
        try {
            String userName = requestDto.getUserName();
            User user = userService.getByUserName(userName);
            String rawPassword = user.getPassword();

            if (!passwordEncoder.matches(requestDto.getPassword(), rawPassword)) {
                throw new BadCredentialsException("Invalid password");
            }

            List<Role> roles = new LinkedList<>();
            Role role = user.getRole();
            roles.add(role);
            String token = jwtTokenProvider.createToken(userName, roles);
            return new AuthenticationResponse(userName, token);
        } catch (NotFoundException exception) {
            throw new UsernameNotFoundException(exception.getMessage(), exception);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

}
