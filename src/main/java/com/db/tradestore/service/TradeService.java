package com.db.tradestore.service;

import java.util.List;
import java.util.Optional;

import com.db.tradestore.entity.Trade;

public interface TradeService
{

   Optional<Trade> getTrade(final String tradeId);

   Trade createOrUpdateTrade(final Trade trade);

   List<Trade> getAllTrade();

   boolean isInValidTradeVersion(final Trade trade);

   void updateExpiredTrades();

   boolean isInValidTradeMaturityDate(final Trade trade);
}
