package com.db.tradestore.schedulingtasks;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.db.tradestore.service.TradeService;

@Component
public class TradeExpiryScheduledTask
{
   private static final Logger log = LoggerFactory.getLogger(TradeExpiryScheduledTask.class);
   private final TradeService tradeService;

   @Autowired
   public TradeExpiryScheduledTask(final TradeService tradeService)
   {
      this.tradeService = tradeService;
   }

   @Scheduled(cron = "0 0 0 * * *")
   public void updateExpiredTrades()
   {
      log.info(String.format("job to updateExpiredTrades running at %s", LocalDateTime.now()));
      tradeService.updateExpiredTrades();
   }
}
