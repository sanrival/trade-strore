package com.db.tradestore.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.db.tradestore.entity.Role;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoleRepositoryTest
{
   @Autowired
   private TestEntityManager entityManager;

   @Autowired
   private RoleRepository roleRepository;

   @Test
   public void testFindById()
   {
      final Role role = new Role("ADMIN");
      final Role persistedRole = entityManager.persist(role);
      final Optional<Role> roleFromDb = roleRepository.findById(persistedRole.getId());
      assertThat(roleFromDb).isPresent().get().isEqualTo(role);
   }

}