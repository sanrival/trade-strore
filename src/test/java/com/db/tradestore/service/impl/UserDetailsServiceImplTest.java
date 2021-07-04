package com.db.tradestore.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;

import com.db.tradestore.entity.Role;
import com.db.tradestore.entity.User;
import com.db.tradestore.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceImplTest
{
   @Mock
   private UserRepository userRepository;
   @InjectMocks
   private UserDetailsServiceImpl userDetailsServiceImpl;

   @Test
   public void loadUserByUsername()
   {
      final String email = "dummy@db.com";
      final User user = new User("dummyU", "dummy@db.com", "dummyp", new Role("Admin"));
      when(userRepository.findByEmail(email)).thenReturn(user);
      final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(email);
      assertThat(userDetails).isNotNull();
      assertThat(userDetails.getUsername()).isEqualTo("dummyU");

   }
}