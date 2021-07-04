package com.db.tradestore.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.db.tradestore.entity.Trade;
import com.db.tradestore.repository.TradeRepository;
import com.db.tradestore.service.TradeService;

@Service
@EnableCaching
public class TradeServiceImpl implements TradeService
{
   private final TradeRepository tradeRepository;

   @Autowired
   public TradeServiceImpl(final TradeRepository tradeRepository)
   {
      this.tradeRepository = tradeRepository;
   }

   @Override
   @Cacheable(value = "trades", key = "#tradeId")
   public Optional<Trade> getTrade(final String tradeId)
   {
      return tradeRepository.findById(tradeId);
   }

   @Override
   @CachePut(value = "trades", key = "#trade.id")
   public Trade createOrUpdateTrade(final Trade trade)
   {
      return tradeRepository.saveAndFlush(trade);
   }

   @Override
   @Cacheable(value = "trades")
   public List<Trade> getAllTrade()
   {
      return tradeRepository.findAll();
   }

   @Override
   public boolean isInValidTradeMaturityDate(final Trade trade)
   {
      return trade.getMaturity().isBefore(LocalDate.now());
   }

   @Override
   public boolean isInValidTradeVersion(final Trade trade)
   {
      final Optional<Trade> existingTrade = getTrade(trade.getId());
      return existingTrade.isPresent() && existingTrade.get().getVersion() > trade.getVersion();
   }

   @Override
   public void updateExpiredTrades()
   {
      tradeRepository.findAll().stream().filter(this::isInValidTradeMaturityDate).forEach(trade -> {
         trade.setExpired(true);
         saveTrade(trade);
      });
   }

   @CacheEvict(value = "trades", condition = "#trade.id")
   public void saveTrade(final Trade trade)
   {
      tradeRepository.saveAndFlush(trade);
   }

}
