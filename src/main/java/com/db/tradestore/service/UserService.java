package com.db.tradestore.service;

import java.util.Collection;
import java.util.Optional;

import com.db.tradestore.entity.User;

public interface UserService
{

   Collection<User> findAll();

   Optional<User> findById(Long id);

   User saveOrUpdate(User user);

   void deleteById(Long id);
}
