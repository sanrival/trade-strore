package com.db.tradestore.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.jparams.verifier.tostring.ToStringVerifier;

public class TradeTest
{
   @Test
   public void testEquals()
   {
      Trade trade = new Trade();
      Trade otherTrade = new Trade();
      assertThat(trade).isEqualTo(otherTrade);
   }

   @Test
   public void testToString()
   {
      ToStringVerifier.forClass(Trade.class).verify();
   }
}