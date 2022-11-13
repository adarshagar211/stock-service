package com.payconiq.service.impl;

import java.util.Date;
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

import com.payconiq.domain.Stock;
import com.payconiq.dto.StockDto;
import com.payconiq.repository.StockRepository;
import com.payconiq.service.StockService;

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
		List<StockDto> stockDtos = stockList.stream().map(x -> EntityToDto(x)).collect(Collectors.toList());
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
		Double currentPrice = Objects.nonNull(stock.getCurrentPrice()) ? stock.getCurrentPrice() : stockDB.getCurrentPrice();
		stockDB = Stock.builder().id(stockDB.getId()).name(name).currentPrice(currentPrice).lastUpdate(new Date()).version(stockDB.getVersion()).build();
		return EntityToDto(stockRepository.save(stockDB));
	}

	private static Stock DtoToEntity(StockDto stockDto) {
		return Stock.builder().id(stockDto.getId()).currentPrice(stockDto.getCurrentPrice())
				              .name(stockDto.getName()).lastUpdate(new Date()).build();
	}
	
    private static StockDto EntityToDto(Stock stock) {
    	return StockDto.builder().id(stock.getId()).currentPrice(stock.getCurrentPrice()).name(stock.getName())
    			                 .lastUpdate(stock.getLastUpdate()).build();
	}
}
