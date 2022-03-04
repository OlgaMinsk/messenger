package com.innowisegroup.messenger.controller;

import com.innowisegroup.messenger.dto.request.AuthenticationRequestDto;
import com.innowisegroup.messenger.exception.NotFoundException;
import com.innowisegroup.messenger.model.Role;
import com.innowisegroup.messenger.model.User;
import com.innowisegroup.messenger.security.MyPasswordEncoder;
import com.innowisegroup.messenger.security.jwt.JwtTokenProvider;
import com.innowisegroup.messenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/messengerAPI/v01/auth/")
public class AuthenticationController {
    //private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;
    private final MyPasswordEncoder passwordEncoder;
    private final UserService userService;

    @Autowired
    public AuthenticationController(
            //AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider,
            MyPasswordEncoder passwordEncoder, UserService userService) {
        //   this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        //TODO в сервис
        try {
            String userName = requestDto.getUserName();
            //       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, requestDto.getPassword()));
            User user = userService.getByUserName(userName);
//todo проверить пароль
            String rawPassword = user.getPassword();

            //passwordEncoder.setPassword(requestDto.getPassword());
            //rawPassword, String encodedPassword
//            System.out.println("user.getPassword() "+user.getPassword());
//            System.out.println("w "+passwordEncoder.setPassword("q"));
//            System.out.println("rawPassword "+rawPassword);
            if (!passwordEncoder.matches((CharSequence) requestDto.getPassword(), rawPassword)) {
                System.out.println("no");
                throw new BadCredentialsException("Invalid rawPassword");
            }

            System.out.println("yes");
            System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            System.out.println("yes");
            List<Role> roles = new LinkedList<>();
            Role role = user.getRole();
            roles.add(role);
            String token = jwtTokenProvider.createToken(userName, roles);
// TODO сделать дто
            Map<Object, Object> response = new HashMap<>();
            response.put("username", userName);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (NotFoundException exception) {
            throw new UsernameNotFoundException(exception.getMessage(), exception);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
