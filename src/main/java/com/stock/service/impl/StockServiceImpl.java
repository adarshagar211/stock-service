package com.stock.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.stock.dto.StockDto;
import com.stock.model.Stock;
import com.stock.repository.StockRepository;
import com.stock.service.StockService;

@Service
@Transactional
public class StockServiceImpl implements StockService {

	@Autowired
	private StockRepository stockRepository;

	public List<StockDto> getAll(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo,pageSize, sort);
		List<Stock> stockList = stockRepository.findAll(pageable).getContent();
		List<StockDto> stockDtos = stockList.stream().map(x -> this.EntityToDto(x)).collect(Collectors.toList());
		return stockDtos;
	}

	@Override
	public StockDto getStock(int id) {
		Stock stock =stockRepository.findById(id)
	                                .orElseThrow(() -> new EntityNotFoundException("Not found in DB Exception"));
		return EntityToDto(stock);
	}

	@Override
	public StockDto createStock(StockDto stockDto) {
		Stock stock = DtoToEntity(stockDto);
		return EntityToDto(stockRepository.save(stock));
	}

	@Override
	public void deleteStock(int id) {
		stockRepository.deleteById(id);
	}

	@Override
	public StockDto updateStock(StockDto stock, int id) {
		Stock stockDB = stockRepository.findById(id)
	                                      .orElseThrow(() -> new EntityNotFoundException("Not found in DB Exception"));
		String name = Objects.nonNull(stock.getName()) ? stock.getName() : stockDB.getName();
		BigDecimal currentPrice = Objects.nonNull(stock.getCurrentPrice()) ? stock.getCurrentPrice() : stockDB.getCurrentPrice();		
		stockDB = Stock.builder().id(stockDB.getId()).name(name).currentPrice(currentPrice).lastUpdate(LocalDateTime.now()).build();
		return EntityToDto(stockRepository.save(stockDB));
	}

	private Stock DtoToEntity(StockDto stockDto) {
		return Stock.builder().id(stockDto.getId()).currentPrice(stockDto.getCurrentPrice())
				              .name(stockDto.getName()).lastUpdate(LocalDateTime.now()).build();
	}
	
    private StockDto EntityToDto(Stock stock) {
    	return StockDto.builder().id(stock.getId()).currentPrice(stock.getCurrentPrice()).name(stock.getName())
    			                 .lastUpdate(stock.getLastUpdate()).build();
	}
}
