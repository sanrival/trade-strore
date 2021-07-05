package com.db.tradestore.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.db.tradestore.entity.Trade;
import com.db.tradestore.exception.InvalidTradeException;
import com.db.tradestore.exception.TradeNotFoundException;
import com.db.tradestore.mapper.TradeModelMapper;
import com.db.tradestore.model.TradeDTO;
import com.db.tradestore.service.TradeService;

import io.swagger.annotations.Api;

@Api(tags = "TradeStore")
@RestController
@RequestMapping(value = "/api/v1")
public class TradeController
{
   private static final Logger logger = LoggerFactory.getLogger(TradeController.class);

   private final TradeService tradeService;

   private final TradeModelMapper tradeModelMapper;

   @Autowired
   public TradeController(final TradeService tradeService, final TradeModelMapper tradeModelMapper)
   {
      this.tradeService = tradeService;
      this.tradeModelMapper = tradeModelMapper;
   }

   @GetMapping(value = "/trade", produces = "application/json")
   @ResponseStatus(HttpStatus.OK)
   public TradeDTO getTrade(@RequestParam("tradeId") String tradeId)
   {
      logger.debug(String.format("received request to get trade with trade id : %s", tradeId));
      final Optional<Trade> trade = tradeService.getTrade(tradeId);
      if (trade.isPresent())
      {
         return tradeModelMapper.convertToDto(trade.get());
      }
      else
      {
         throw new TradeNotFoundException("Cannot find trade with given id");
      }
   }

   @GetMapping(value = "/trades", produces = "application/json")
   @ResponseStatus(HttpStatus.OK)
   public List<TradeDTO> getAllTrade()
   {
      logger.debug("received request to get all trades");
      return tradeService.getAllTrade().stream().map(tradeModelMapper::convertToDto).collect(Collectors.toList());
   }

   @PutMapping(value = "/trade", produces = "application/json")
   @ResponseStatus(HttpStatus.OK)
   public TradeDTO createOrUpdateTrade(@RequestBody() TradeDTO tradeDTO)
   {
      logger.debug(String.format("received request to update trade with trade id :%s", tradeDTO.getId()));
      Trade trade = tradeModelMapper.convertToEntity(tradeDTO);
      if (tradeService.isInValidTradeMaturityDate(trade))
      {
         throw new InvalidTradeException("Invalid Request. Trade Maturity Date is earlier than current.");
      }

      if (tradeService.isInValidTradeVersion(trade))
      {
         throw new InvalidTradeException("Invalid Request. Trade Version is lower than current.");
      }

      if (trade.getCreated() == null)
      {
         trade.setCreated(LocalDate.now());
      }

      return tradeModelMapper.convertToDto(tradeService.createOrUpdateTrade(trade));
   }
}
