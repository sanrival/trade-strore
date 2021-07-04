package com.db.tradestore.model;

import java.time.LocalDate;
import java.util.Objects;

public class TradeDTO
{
   private String id;
   private int version;
   private String counterpartyId;
   private String bookId;
   private LocalDate maturity;
   private LocalDate created;
   private boolean expired;

   public String getId()
   {
      return id;
   }

   public void setId(final String id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   public String getCounterpartyId()
   {
      return counterpartyId;
   }

   public void setCounterpartyId(final String counterpartyId)
   {
      this.counterpartyId = counterpartyId;
   }

   public String getBookId()
   {
      return bookId;
   }

   public void setBookId(final String bookId)
   {
      this.bookId = bookId;
   }

   public LocalDate getMaturity()
   {
      return maturity;
   }

   public void setMaturity(final LocalDate maturity)
   {
      this.maturity = maturity;
   }

   public LocalDate getCreated()
   {
      return created;
   }

   public void setCreated(final LocalDate created)
   {
      this.created = created;
   }

   public boolean isExpired()
   {
      return expired;
   }

   public void setExpired(final boolean expired)
   {
      this.expired = expired;
   }

   @Override
   public boolean equals(final Object o)
   {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      final TradeDTO tradeDTO = (TradeDTO) o;
      return getVersion() == tradeDTO.getVersion() && isExpired() == tradeDTO.isExpired() && Objects.equals(getId(), tradeDTO.getId()) && Objects
            .equals(getCounterpartyId(), tradeDTO.getCounterpartyId()) && Objects.equals(getBookId(), tradeDTO.getBookId()) && Objects.equals(getMaturity(), tradeDTO.getMaturity())
            && Objects.equals(getCreated(), tradeDTO.getCreated());
   }

   @Override
   public int hashCode()
   {
      return Objects.hash(getId(), getVersion(), getCounterpartyId(), getBookId(), getMaturity(), getCreated(), isExpired());
   }

   @Override
   public String toString()
   {
      return "TradeDTO{" +
            "id=" + id +
            ", version=" + version +
            ", counterpartyId=" + counterpartyId +
            ", bookId=" + bookId +
            ", maturity=" + maturity +
            ", created=" + created +
            ", expired=" + expired +
            '}';
   }
}
