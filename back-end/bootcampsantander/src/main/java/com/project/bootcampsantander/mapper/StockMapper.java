package com.project.bootcampsantander.mapper;

import com.project.bootcampsantander.model.Stock;
import com.project.bootcampsantander.model.dto.StockDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockMapper {

    //Transformando o dto em entidade
    public Stock toEntity(StockDTO dto) {
        Stock stock = new Stock();
        stock.setId(dto.getId());
        stock.setName(dto.getName());
        stock.setPrice(dto.getPrice());
        stock.setVariation(dto.getVariation());
        stock.setDate(dto.getDate());

        return stock;
    }

    //conversão de entidade para dto
    public StockDTO toDto(Stock stock) {
        StockDTO dto = new StockDTO();
        dto.setId(stock.getId());
        dto.setName(stock.getName());
        dto.setPrice(stock.getPrice());
        dto.setVariation(stock.getVariation());
        dto.setDate(stock.getDate());
        return dto;
    }
    //recebe uma lista de entidades, retorna uma lista de dtos
    public List<StockDTO> toDto(List<Stock> listStock){
        //stream percorre toda a lista/
        // map olha item por item, pega e manda pro toDto/
        // collection transformar em uma lista de retorno
        return listStock.stream().map(this::toDto).collect(Collectors.toList());
    }
}
