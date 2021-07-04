package com.db.tradestore.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.db.tradestore.entity.Role;
import com.db.tradestore.entity.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest
{
   @Autowired
   private TestEntityManager entityManager;

   @Autowired
   private UserRepository userRepository;

   @Test
   public void testFindByEmail()
   {
      final Role role = new Role("DUMMY");
      final User user = new User("dummyU", "dummy@db.com", "dummyp", role);
      role.addUser(user);
      entityManager.persist(role);
      entityManager.persist(user);
      final User userFromDb = userRepository.findByEmail("dummy@db.com");
      assertThat(userFromDb).isEqualTo(user);
   }
}