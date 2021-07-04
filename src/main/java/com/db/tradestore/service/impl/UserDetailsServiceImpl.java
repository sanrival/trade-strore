package com.db.tradestore.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.db.tradestore.entity.User;
import com.db.tradestore.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{

   private final UserRepository userRepository;

   @Autowired
   public UserDetailsServiceImpl(final UserRepository userRepository)
   {
      this.userRepository = userRepository;
   }

   @Override
   public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
   {
      User user = userRepository.findByEmail(email);
      if (user == null)
      {
         throw new UsernameNotFoundException("Email " + email + " Not Found");
      }
      return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), getGrantedAuthority(user));
   }

   private Collection<GrantedAuthority> getGrantedAuthority(User user)
   {
      Collection<GrantedAuthority> authorities = new ArrayList<>();
      if (user.getRole().getName().equalsIgnoreCase("ADMIN"))
      {
         authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
      }
      authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
      return authorities;
   }

}
