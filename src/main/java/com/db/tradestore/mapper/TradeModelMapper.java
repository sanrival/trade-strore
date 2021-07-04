package com.db.tradestore.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.db.tradestore.entity.Trade;
import com.db.tradestore.model.TradeDTO;

@Component
public class TradeModelMapper
{
   private final ModelMapper modelMapper;

   public TradeModelMapper()
   {
      this.modelMapper = new ModelMapper();
   }

   public TradeDTO convertToDto(Trade trade)
   {
      return modelMapper.map(trade, TradeDTO.class);
   }

   public Trade convertToEntity(TradeDTO tradeDTO)
   {
      return modelMapper.map(tradeDTO, Trade.class);
   }

}
