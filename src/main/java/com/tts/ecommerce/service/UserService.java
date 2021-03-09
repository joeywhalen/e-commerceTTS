package com.tts.ecommerce.service;

import com.tts.ecommerce.model.User;
import com.tts.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  public User findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public void saveNew(User user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
  }

  public void saveExisting(User user) {
    userRepository.save(user);
  }

  public User getLoggedInUser() {
    return findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
  }


}
