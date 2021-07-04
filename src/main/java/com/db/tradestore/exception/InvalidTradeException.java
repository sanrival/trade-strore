package com.db.tradestore.exception;

public class InvalidTradeException extends RuntimeException
{

   public InvalidTradeException(String errorMessage)
   {
      super(errorMessage);
   }
}
