package com.db.tradestore.exception;

public class TradeNotFoundException extends RuntimeException
{
   public TradeNotFoundException(String errorMessage)
   {
      super(errorMessage);
   }
}
