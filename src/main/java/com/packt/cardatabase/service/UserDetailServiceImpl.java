package com.packt.cardatabase.service;

import com.packt.cardatabase.domain.User;
import com.packt.cardatabase.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
  @Autowired UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User currentUser = userRepository.findByUsername(username);

    return new org.springframework.security.core.userdetails.User(
        username,
        currentUser.getPassword(),
        true,
        true,
        true,
        true,
        AuthorityUtils.createAuthorityList(currentUser.getRole()));
  }
}
