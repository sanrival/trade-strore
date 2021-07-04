package com.db.tradestore.handler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.db.tradestore.exception.InvalidTradeException;
import com.db.tradestore.exception.TradeNotFoundException;

class TradeExceptionHandlerTest
{
   private final TradeExceptionHandler tradeExceptionHandler = new TradeExceptionHandler();

   @Test
   void handleInvalidTradeException()
   {
      InvalidTradeException exception = new InvalidTradeException("Trade Maturity Date is earlier than current.");
      final ResponseEntity<Object> objectResponseEntity = tradeExceptionHandler.handleInvalidTradeException(exception);
      assertThat(objectResponseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
      assertThat(objectResponseEntity.getBody()).isEqualTo("Trade Maturity Date is earlier than current.");
   }

   @Test
   void handleTradeNotFoundException()
   {
      TradeNotFoundException exception = new TradeNotFoundException("Cannot find trade with given id.");
      final ResponseEntity<Object> objectResponseEntity = tradeExceptionHandler.handleTradeNotFoundException(exception);
      assertThat(objectResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
      assertThat(objectResponseEntity.getBody()).isEqualTo("Cannot find trade with given id.");
   }
}