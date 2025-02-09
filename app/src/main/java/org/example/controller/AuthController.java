package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.entities.RefreshToken;
import org.example.model.Response.JwtResponseDTO;
import org.example.model.UserInfoDTO;
import org.example.service.JwtService;
import org.example.service.RefreshTokenService;
import org.example.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@AllArgsConstructor
@RestController
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @PostMapping("/api/v1/signup")
    public ResponseEntity SignUp(@RequestBody UserInfoDTO userInfoDTO) {
        try {
            String userId = userDetailsServiceImpl.signUpUser(userInfoDTO);
            if(Objects.isNull(userId)) {
                return new ResponseEntity<>("Already exists", HttpStatus.BAD_REQUEST);
            }
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userInfoDTO.getUsername());
            String jwtToken =jwtService.generateToken(userInfoDTO.getUsername());
            return new ResponseEntity<>(JwtResponseDTO.builder()
                    .accessToken(jwtToken).token(refreshToken.getToken()).build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Exception in user service", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
