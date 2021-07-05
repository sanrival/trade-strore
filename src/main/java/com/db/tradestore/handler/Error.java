package com.db.tradestore.handler;

import java.util.Objects;

public class Error
{
   private String message;
   private Integer Status;

   public Error(final String message, final Integer status)
   {
      this.message = message;
      Status = status;
   }

   public String getMessage()
   {
      return message;
   }

   public void setMessage(final String message)
   {
      this.message = message;
   }

   public Integer getStatus()
   {
      return Status;
   }

   @Override
   public boolean equals(final Object o)
   {
      if (this == o)
         return true;
      if (!(o instanceof Error))
         return false;
      final Error error = (Error) o;
      return Objects.equals(getMessage(), error.getMessage()) && Objects.equals(getStatus(), error.getStatus());
   }

   @Override
   public String toString()
   {
      return "Error{" +
            "message='" + message + '\'' +
            ", Status=" + Status +
            '}';
   }

   @Override
   public int hashCode()
   {
      return Objects.hash(getMessage(), getStatus());
   }

   public void setStatus(final Integer status)
   {
      Status = status;
   }

}
