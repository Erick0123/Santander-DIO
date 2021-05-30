package com.project.bootcampsantander.service;

import com.project.bootcampsantander.exceptions.BusinessException;
import com.project.bootcampsantander.exceptions.NotFoundException;
import com.project.bootcampsantander.mapper.StockMapper;
import com.project.bootcampsantander.model.Stock;
import com.project.bootcampsantander.model.dto.StockDTO;
import com.project.bootcampsantander.repository.StockRepository;
import com.project.bootcampsantander.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository repository;

    @Autowired
    private StockMapper mapper;

    /*controla a transação de forma automatica para o banco de dados.
     Ele abre a transação faz um insert e fecha a
      transação para não ficar com as transaçoes abertas*/
    @Transactional
    public StockDTO save(StockDTO dto) {
        Optional<Stock> optionalStock = repository.findByNameAndDate(dto.getName(), dto.getDate());

        //não pode ter doisou mais dados com mesmo nome e mesma data
        if (optionalStock.isPresent()){
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
        }

        /*toEntity foi é um metodo criado manualmente pela class StockMapper
        para transformar o dto em uma entidade, pois o DB só cria tabelas se forem entidades*/
        Stock stock = mapper.toEntity(dto);

        //insert
        repository.save(stock);
        return mapper.toDto(stock);

    }

    @Transactional
    public StockDTO update(StockDTO dto) {
        Optional<Stock> optionalStock = repository.findByStockUpdate(dto.getName(), dto.getDate(), dto.getId());
        if (optionalStock.isPresent()){
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
        }
        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        return mapper.toDto(stock);
    }

    //esse readOnly está dizendo que esse método é so um select, nao pode ser alterado somente visto
    @Transactional(readOnly = true)
    public List<StockDTO> findAll() {
        return mapper.toDto(repository.findAll());
    }

    @Transactional(readOnly = true)
    public StockDTO findById(Long id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public StockDTO delete(Long id) {
        //chama o metodo find e vasculha se tem o id que solicitou
        StockDTO dto = this.findById(id);
        //caso tenha delete
        repository.deleteById(dto.getId());
        return dto;
    }
    @Transactional(readOnly = true)
    public List<StockDTO> findByToday() {
        return repository.findByToday(LocalDate.now()).map(mapper::toDto)
                .orElseThrow(NotFoundException::new);
    }
}
