package com.db.tradestore.service.impl;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.db.tradestore.entity.User;
import com.db.tradestore.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest
{
   @Mock
   private UserRepository userRepository;
   @InjectMocks
   private UserServiceImpl userService;

   @Test
   public void findAll()
   {
      when(userRepository.findAll()).thenReturn(null);
      userService.findAll();
      verify(userRepository).findAll();
   }

   @Test
   public void findById()
   {
      Long id = 1L;
      when(userRepository.findById(id)).thenReturn(null);
      userService.findById(1L);
      verify(userRepository).findById(id);
   }

   @Test
   public void saveOrUpdate()
   {
      when(userRepository.saveAndFlush(Mockito.any(User.class))).thenReturn(null);
      userService.saveOrUpdate(new User());
      verify(userRepository).saveAndFlush(Mockito.any(User.class));
   }

   @Test
   public void deleteById()
   {
      Long id = 1L;
      userService.deleteById(id);
      verify(userRepository).deleteById(id);
   }

   @Test
   public void deleteById_throws_error()
   {
      Long id = 1L;
      doThrow(new RuntimeException()).when(userRepository).deleteById(id);
      userService.deleteById(id);
      verify(userRepository).deleteById(id);
   }
}