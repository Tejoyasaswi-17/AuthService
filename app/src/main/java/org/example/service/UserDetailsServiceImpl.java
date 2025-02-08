package org.example.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.entities.UserInfo;
import org.example.model.UserInfoDTO;
import org.example.repository.UserRepository;
import org.example.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;


@Data
@Component
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Couldn't find the user!!");
        }
        return new CustomUserDetails(user);
    }

    public UserInfo checkIfUserAlreadyExists(UserInfoDTO userinfoDTO) {
        return userRepository.findByUsername(userinfoDTO.getUsername());
    }

    public Boolean signUpUser(UserInfoDTO userInfoDTO) {
        if (!ValidationUtil.validateUserDetails(userInfoDTO.getEmail(), userInfoDTO.getPassword())) {
            return false;
        }
        userInfoDTO.setPassword(passwordEncoder.encode(userInfoDTO.getPassword()));
        if (Objects.nonNull(checkIfUserAlreadyExists(userInfoDTO))) {
            return false;
        }
        String userId = UUID.randomUUID().toString();
        userRepository.save(new UserInfo(userId, userInfoDTO.getUsername(), userInfoDTO.getPassword(), new HashSet<>()));
        return false;
    }
}
