package com.db.tradestore.entity;

import org.junit.Test;

import com.jparams.verifier.tostring.ToStringVerifier;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class RoleTest
{
   @Test
   public void testEquals()
   {
      final Role role1 = new Role("ADMIN");
      final Role role2 = new Role("USER");
      final User user1 = new User("dummyU1", "dummy1@db.com", "dummyp1", role1);
      final User user2 = new User("dummyU2", "dummy2@db.com", "dummyp2", role2);
      EqualsVerifier.forClass(Role.class)
            .suppress(Warning.SURROGATE_KEY)
            .withPrefabValues(User.class, user1, user2)
            .verify();
   }

   @Test
   public void testToString()
   {
      ToStringVerifier.forClass(Role.class).withIgnoredFields("users").verify();
   }
}