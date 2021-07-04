package com.db.tradestore.entity;

import org.junit.Test;

import com.jparams.verifier.tostring.ToStringVerifier;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class UserTest
{
   @Test
   public void testEquals()
   {
      final Role role1 = new Role("ADMIN");
      final Role role2 = new Role("USER");
      EqualsVerifier.forClass(User.class)
            .usingGetClass()
            .suppress(Warning.SURROGATE_KEY)
            .withPrefabValues(Role.class, role1, role2)
            .verify();
   }

   @Test
   public void testToString()
   {
      ToStringVerifier.forClass(User.class).withIgnoredFields("role").verify();
   }
}