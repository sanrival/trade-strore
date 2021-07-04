package com.db.tradestore.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.db.tradestore.model.TradeDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@WithMockUser(roles = "USER")
@ContextConfiguration(classes = { ITTestConfig.class })
public class TradeControllerIT
{
   @Autowired
   private TestRestTemplate restTemplate;

   @LocalServerPort
   int randomServerPort;

   @Test
   public void getTrade()
   {
      final ResponseEntity<TradeDTO> forEntity = restTemplate.withBasicAuth("kamal@db.com", "valecha").getForEntity("/api/v1/trade?tradeId=T1", TradeDTO.class);
      assertThat(forEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
   }

   @Test
   public void getAllTrade()
   {
      final ResponseEntity<TradeDTO[]> forEntity = restTemplate.withBasicAuth("kamal@db.com", "valecha").getForEntity("/api/v1/trades", TradeDTO[].class);
      assertThat(forEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
   }

   @Test
   public void createOrUpdateTrade()
   {
      TradeDTO tradeDTO = new TradeDTO();
      tradeDTO.setId("T2");
      tradeDTO.setVersion(2);
      tradeDTO.setCounterpartyId("CP-1");
      tradeDTO.setBookId("B1");
      tradeDTO.setExpired(false);
      LocalDate tomorrow = LocalDate.now().plusDays(1);
      tradeDTO.setMaturity(tomorrow);

      HttpHeaders headers = new HttpHeaders();
      headers.set("X-COM-PERSIST", "true");

      HttpEntity<TradeDTO> request = new HttpEntity<>(tradeDTO, headers);
      restTemplate.withBasicAuth("kamal@db.com", "valecha").put("/api/v1/trade/", request);

      final ResponseEntity<TradeDTO> forEntity = restTemplate.withBasicAuth("kamal@db.com", "valecha").getForEntity("/api/v1/trade?tradeId=T2", TradeDTO.class);
      assertThat(forEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
      assertThat(forEntity.getBody().getId()).isEqualTo("T2");
   }

   @Test
   public void createOrUpdateTrade_unuthorized()
   {
      final ResponseEntity<Void> forEntity = restTemplate.getForEntity("/api/v1/trade?tradeId=T1", Void.class);
      assertThat(forEntity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
   }
}