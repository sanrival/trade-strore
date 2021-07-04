package com.db.tradestore.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.db.tradestore.entity.Trade;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TradeRepositoryTest
{
   @Autowired
   private TestEntityManager entityManager;

   @Autowired
   private TradeRepository tradeRepository;

   @Test
   public void testFindById()
   {
      final Trade trade = new Trade("T1", 1, "CP-1", "B1", LocalDate.now().plusDays(1), LocalDate.now(), false);
      entityManager.persist(trade);
      final Optional<Trade> tradeFromDb = tradeRepository.findById("T1");
      assertThat(tradeFromDb).isPresent().get().isEqualTo(trade);
   }

}