package com.spearhead.springsecurity.security;

import com.spearhead.springsecurity.user.entity.UserEntity;
import com.spearhead.springsecurity.user.model.User;
import com.spearhead.springsecurity.user.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        User user = fromUserEntity(userEntity);
        if (user != null) {
            return user;
        }

        throw new UsernameNotFoundException("User with username " + username + " not found");
    }

    private User fromUserEntity(UserEntity userEntity) {
        User user = new User(userEntity.getUsername(), userEntity.getPassword(), userEntity.isEnabled());
        user.setAuthorities(userEntity.getAuthorityEntities().stream().map(authorityEntity ->
                new SimpleGrantedAuthority(authorityEntity.getAuthority())).collect(Collectors.toSet()));
        return user;
    }
}
