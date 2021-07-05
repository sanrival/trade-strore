package com.db.tradestore.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.db.tradestore.exception.InvalidTradeException;
import com.db.tradestore.exception.TradeNotFoundException;

@ControllerAdvice
public class TradeExceptionHandler extends ResponseEntityExceptionHandler
{
   @ExceptionHandler({ InvalidTradeException.class })
   public ResponseEntity<Object> handleInvalidTradeException(Exception ex)
   {
      return ResponseEntity.status(HttpStatus.OK).body(new Error(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));

   }

   @ExceptionHandler({ TradeNotFoundException.class })
   public ResponseEntity<Object> handleTradeNotFoundException(Exception ex)
   {
      return ResponseEntity.status(HttpStatus.OK).body(new Error(ex.getMessage(), HttpStatus.NOT_FOUND.value()));
   }

}
