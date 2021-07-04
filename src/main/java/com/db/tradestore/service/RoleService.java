package com.db.tradestore.service;

import java.util.Collection;
import java.util.Optional;

import com.db.tradestore.entity.Role;

public interface RoleService
{
   Collection<Role> findAll();

   Optional<Role> findById(Long id);

   Role saveOrUpdate(Role role);

   void deleteById(Long id);
}
