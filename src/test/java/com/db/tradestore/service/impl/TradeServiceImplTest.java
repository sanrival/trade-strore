package com.db.tradestore.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.db.tradestore.entity.Trade;
import com.db.tradestore.repository.TradeRepository;

@RunWith(MockitoJUnitRunner.class)
public class TradeServiceImplTest
{
   @Mock
   private TradeRepository tradeRepository;
   @InjectMocks
   private TradeServiceImpl tradeService;

   @Test
   public void getTrade()
   {
      String id = "T1";
      when(tradeRepository.findById(id)).thenReturn(null);
      tradeService.getTrade("T1");
      verify(tradeRepository).findById(id);
   }

   @Test
   public void createOrUpdateTrade()
   {
      when(tradeRepository.saveAndFlush(Mockito.any(Trade.class))).thenReturn(null);
      tradeService.createOrUpdateTrade(new Trade());
      verify(tradeRepository).saveAndFlush(Mockito.any(Trade.class));
   }

   @Test
   public void getAllTrade()
   {
      when(tradeRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
      tradeService.getAllTrade();
      verify(tradeRepository).findAll();
   }

   @Test
   public void isInValidTradeMaturityDateWithPastMaturityDate()
   {
      LocalDate yesterday = LocalDate.now().minusDays(1);
      Trade trade = new Trade("T1", 1, "CP-1", "B1", yesterday, yesterday, false);
      final boolean inValidTradeMaturityDate = tradeService.isInValidTradeMaturityDate(trade);
      assertThat(inValidTradeMaturityDate).isTrue();
   }

   @Test
   public void isInValidTradeMaturityDateWithFutureMaturityDate()
   {
      LocalDate yesterday = LocalDate.now();
      Trade trade = new Trade("T1", 1, "CP-1", "B1", yesterday, yesterday, false);
      final boolean inValidTradeMaturityDate = tradeService.isInValidTradeMaturityDate(trade);
      assertThat(inValidTradeMaturityDate).isFalse();
   }

   @Test
   public void isInValidTradeVersionForTradeWithOlderVersion()
   {
      LocalDate date = LocalDate.now();
      Trade trade = new Trade("T1", 1, "CP-1", "B1", date, date, false);
      Trade tradeInDb = new Trade("T1", 2, "CP-1", "B1", date, date, false);
      when(tradeRepository.findById(trade.getId())).thenReturn(Optional.of(tradeInDb));
      final boolean isInValidTradeVersion = tradeService.isInValidTradeVersion(trade);
      assertThat(isInValidTradeVersion).isTrue();
   }

   @Test
   public void isInValidTradeVersionForTradeWithNotOlderVersion()
   {
      LocalDate date = LocalDate.now();
      Trade trade = new Trade("T1", 2, "CP-1", "B1", date, date, false);
      Trade tradeInDb = new Trade("T1", 2, "CP-1", "B1", date, date, false);
      when(tradeRepository.findById(trade.getId())).thenReturn(Optional.of(tradeInDb));
      final boolean isInValidTradeVersion = tradeService.isInValidTradeVersion(trade);
      assertThat(isInValidTradeVersion).isFalse();
   }

   @Test
   public void updateExpiredTrades()
   {
      LocalDate yesterday = LocalDate.now().minusDays(1);
      LocalDate today = LocalDate.now();
      Trade trade1 = new Trade("T1", 1, "CP-1", "B1", yesterday, yesterday, false);
      Trade trade2 = new Trade("T2", 1, "CP-2", "B2", today, today, false);
      List<Trade> trades = new ArrayList<>();
      trades.add(trade1);
      trades.add(trade2);
      when(tradeRepository.findAll()).thenReturn(trades);
      tradeService.updateExpiredTrades();
      assertThat(trade1.isExpired()).isTrue();
      assertThat(trade2.isExpired()).isFalse();
   }

   @Test
   public void saveTrade()
   {
      when(tradeRepository.saveAndFlush(Mockito.any(Trade.class))).thenReturn(null);
      tradeService.saveTrade(new Trade());
      verify(tradeRepository).saveAndFlush(Mockito.any(Trade.class));
   }
}