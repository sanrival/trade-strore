package com.db.tradestore.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.db.tradestore.entity.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserRepositoryTest
{
   @Autowired
   private TestEntityManager entityManager;

   @Autowired
   private UserRepository userRepository;

   @Test
   public void testFindByEmail()
   {
      final User userFromDb = userRepository.findByEmail("test@db.com");
      assertThat(userFromDb.getEmail()).isEqualTo("test@db.com");
   }
}