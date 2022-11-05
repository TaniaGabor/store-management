package ing.com.storemanagementapi.controller;

import ing.com.storemanagementapi.dto.AuthDto;
import ing.com.storemanagementapi.dto.JwtTokenDto;
import ing.com.storemanagementapi.exception.ApiInvalidCredentialsException;
import ing.com.storemanagementapi.security.JwtUtil;
import ing.com.storemanagementapi.service.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/v1/")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;


    @PostMapping("/auth")
    public JwtTokenDto generateToken(@Valid @RequestBody AuthDto authDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword())
            );
        } catch (ApiInvalidCredentialsException e) {
            throw new ApiInvalidCredentialsException();

        }
        log.info("Token was generated");
        return JwtTokenDto.builder()
                .token(jwtUtil.generateToken(userService.getUserByUsername(authDto.getUsername())))
                .build();
    }
}
