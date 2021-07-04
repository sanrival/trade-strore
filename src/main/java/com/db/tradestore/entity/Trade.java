package com.db.tradestore.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Trade
{
   @Id
   private String id;
   private int version;
   private String counterpartyId;
   private String bookId;
   private LocalDate maturity;
   private LocalDate created;
   private boolean expired;

   public Trade()
   {

   }

   public Trade(final String id, final int version, final String counterpartyId, final String bookId, final LocalDate maturity, final LocalDate created, final boolean expired)
   {
      this.id = id;
      this.version = version;
      this.counterpartyId = counterpartyId;
      this.bookId = bookId;
      this.maturity = maturity;
      this.created = created;
      this.expired = expired;
   }

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
      if (!(o instanceof Trade))
         return false;
      final Trade trade = (Trade) o;
      return getVersion() == trade.getVersion() && isExpired() == trade.isExpired() && Objects.equals(getId(), trade.getId()) && Objects
            .equals(getCounterpartyId(), trade.getCounterpartyId()) && Objects.equals(getBookId(), trade.getBookId()) && Objects.equals(getMaturity(), trade.getMaturity())
            && Objects.equals(getCreated(), trade.getCreated());
   }

   @Override
   public int hashCode()
   {
      return Objects.hash(getId(), getVersion(), getCounterpartyId(), getBookId(), getMaturity(), getCreated(), isExpired());
   }

   @Override
   public String toString()
   {
      return "Trade{" +
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
