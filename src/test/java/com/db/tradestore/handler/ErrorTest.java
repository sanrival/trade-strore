package com.db.tradestore.handler;

import org.junit.Test;

import com.jparams.verifier.tostring.ToStringVerifier;

public class ErrorTest
{

   @Test
   public void testToString()
   {
      ToStringVerifier.forClass(Error.class).verify();
   }
}