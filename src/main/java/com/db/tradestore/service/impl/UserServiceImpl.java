package com.db.tradestore.service.impl;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.db.tradestore.entity.User;
import com.db.tradestore.repository.UserRepository;
import com.db.tradestore.service.UserService;

@Service
@EnableCaching
public class UserServiceImpl implements UserService
{
   private final UserRepository userRepository;

   @Autowired
   public UserServiceImpl(final UserRepository userRepository)
   {
      this.userRepository = userRepository;
   }

   @Override
   @Cacheable(value = "users")
   public Collection<User> findAll()
   {
      return userRepository.findAll();
   }

   @Override
   @Cacheable(value = "users", key = "#id")
   public Optional<User> findById(Long id)
   {
      return userRepository.findById(id);
   }

   @Override
   @CachePut(value = "users", key = "#user.id")
   public User saveOrUpdate(User user)
   {
      return userRepository.saveAndFlush(user);
   }

   @Override
   @CacheEvict(value = "users", key = "#id")
   public void deleteById(Long id)
   {
      try
      {
         userRepository.deleteById(id);
      }
      catch (Exception exception)
      {
         exception.printStackTrace();
      }
   }
}
