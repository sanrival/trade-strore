package com.db.tradestore.service.impl;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.db.tradestore.entity.Role;
import com.db.tradestore.repository.RoleRepository;
import com.db.tradestore.service.RoleService;

@Service
@EnableCaching
public class RoleServiceImpl implements RoleService
{
   private final RoleRepository roleRepository;

   @Autowired
   public RoleServiceImpl(final RoleRepository roleRepository)
   {
      this.roleRepository = roleRepository;
   }

   @Override
   @Cacheable(value = "roles")
   public Collection<Role> findAll()
   {
      return roleRepository.findAll();
   }

   @Override
   @Cacheable(value = "roles", key = "#id")
   public Optional<Role> findById(Long id)
   {
      return roleRepository.findById(id);
   }

   @Override
   @CachePut(value = "roles", key = "#role.id")
   public Role saveOrUpdate(Role role)
   {
      return roleRepository.saveAndFlush(role);
   }

   @Override
   @CacheEvict(value = "roles", key = "#id")
   public void deleteById(Long id)
   {
      try
      {
         roleRepository.deleteById(id);
      }
      catch (Exception exception)
      {
         exception.printStackTrace();
      }
   }
}
