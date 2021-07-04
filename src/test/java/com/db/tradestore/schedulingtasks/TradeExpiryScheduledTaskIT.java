package com.db.tradestore.schedulingtasks;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.scheduling.config.ScheduledTaskHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
public class TradeExpiryScheduledTaskIT
{
   @Autowired
   private ScheduledTaskHolder scheduledTaskHolder;

   @MockBean(name = "mvcHandlerMappingIntrospector")
   private HandlerMappingIntrospector handlerMappingIntrospector;

   @Test
   public void testUpdateExpiredTradesCronTaskScheduled()
   {
      Set<ScheduledTask> scheduledTasks = scheduledTaskHolder.getScheduledTasks();
      scheduledTasks.forEach(scheduledTask -> scheduledTask.getTask().getRunnable().getClass().getDeclaredMethods());
      long count = scheduledTasks.stream()
            .filter(scheduledTask -> scheduledTask.getTask() instanceof CronTask)
            .map(scheduledTask -> (CronTask) scheduledTask.getTask())
            .filter(cronTask -> cronTask.getExpression().equals("0 0 0 * * *") && cronTask.toString().equals("com.db.tradestore.schedulingtasks.TradeExpiryScheduledTask.updateExpiredTrades"))
            .count();
      assertThat(count).isEqualTo(1L);
   }
}
