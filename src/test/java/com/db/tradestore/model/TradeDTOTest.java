package com.db.tradestore.model;

import org.junit.Test;

import com.jparams.verifier.tostring.ToStringVerifier;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class TradeDTOTest
{
   @Test
   public void testEquals()
   {
      EqualsVerifier.forClass(TradeDTO.class)
            .usingGetClass()
            .suppress(Warning.NONFINAL_FIELDS)
            .verify();
   }

   @Test
   public void testToString()
   {
      ToStringVerifier.forClass(TradeDTO.class).verify();
   }
}