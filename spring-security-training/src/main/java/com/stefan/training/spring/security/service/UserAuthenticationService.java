package com.stefan.training.spring.security.service;

import com.stefan.training.spring.security.model.AuthenticatedUser;
import com.stefan.training.spring.security.model.User;
import com.stefan.training.spring.security.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserAuthenticationService implements UserDetailsService {

    @Resource
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByName(username);

        return new AuthenticatedUser(
                user.getUsername(), user.getPassword(), user.getEncryptedPassword(), user.getRoles());
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
