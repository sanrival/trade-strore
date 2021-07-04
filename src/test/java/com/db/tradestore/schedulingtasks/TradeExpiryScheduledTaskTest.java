package com.db.tradestore.schedulingtasks;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.support.CronTrigger;

import com.db.tradestore.service.TradeService;

@RunWith(MockitoJUnitRunner.class)
public class TradeExpiryScheduledTaskTest
{
   @Mock
   private TradeService tradeService;

   @InjectMocks
   private TradeExpiryScheduledTask tradeExpiryScheduledTask;

   @Test
   public void testUpdateExpiredTrades()
   {
      doNothing().when(tradeService).updateExpiredTrades();
      tradeExpiryScheduledTask.updateExpiredTrades();
      verify(tradeService).updateExpiredTrades();
   }

   @Test
   public void testCronIsRunningMidnightEveryDay()
   {
      CronTrigger trigger = new CronTrigger("0 0 0 * * *");
      Calendar today = Calendar.getInstance();

      SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
      final Date yesterday = today.getTime();
      Date nextExecutionTime = trigger.nextExecutionTime(
            new TriggerContext()
            {

               @Override
               public Date lastScheduledExecutionTime()
               {
                  return yesterday;
               }

               @Override
               public Date lastActualExecutionTime()
               {
                  return yesterday;
               }

               @Override
               public Date lastCompletionTime()
               {
                  return yesterday;
               }
            });
      assertThat(df.format(nextExecutionTime)).isEqualTo("00:00:00");
      assertThat(nextExecutionTime).isNotNull();
      assertThat(yesterday.getDate() + 1).isEqualTo(nextExecutionTime.getDate());
   }

}