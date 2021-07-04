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

import com.db.tradestore.entity.Role;
import com.db.tradestore.repository.RoleRepository;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceImplTest
{
   @Mock
   private RoleRepository roleRepository;
   @InjectMocks
   private RoleServiceImpl roleService;

   @Test
   public void findAll()
   {
      when(roleRepository.findAll()).thenReturn(null);
      roleService.findAll();
      verify(roleRepository).findAll();
   }

   @Test
   public void findById()
   {
      Long id = 1L;
      when(roleRepository.findById(id)).thenReturn(null);
      roleService.findById(1L);
      verify(roleRepository).findById(id);
   }

   @Test
   public void saveOrUpdate()
   {
      when(roleRepository.saveAndFlush(Mockito.any(Role.class))).thenReturn(null);
      roleService.saveOrUpdate(new Role("dummy"));
      verify(roleRepository).saveAndFlush(Mockito.any(Role.class));
   }

   @Test
   public void deleteById()
   {
      Long id = 1L;
      roleService.deleteById(id);
      verify(roleRepository).deleteById(id);
   }

   @Test
   public void deleteById_throws_error()
   {
      Long id = 1L;
      doThrow(new RuntimeException()).when(roleRepository).deleteById(id);
      roleService.deleteById(id);
      verify(roleRepository).deleteById(id);
   }
}