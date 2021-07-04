package com.db.tradestore.handler;

import org.springframework.http.HttpHeaders;
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
      return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(),
            HttpStatus.BAD_REQUEST);
   }

   @ExceptionHandler({ TradeNotFoundException.class })
   public ResponseEntity<Object> handleTradeNotFoundException(Exception ex)
   {
      return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(),
            HttpStatus.NOT_FOUND);
   }

}
