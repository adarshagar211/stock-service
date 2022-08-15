package com.payconiq.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.payconiq.model.Stock;
import com.payconiq.repository.StockRepository;
import com.payconiq.service.StockService;

@Service
@Transactional
public class StockServiceImpl implements StockService {

	@Autowired
	private StockRepository stockRepository;

	public List<Stock> getAll(Integer pageNumber) {
		int page = pageNumber == null ? 0 : pageNumber;
		Pageable pageable = PageRequest.of(page,50);
		return stockRepository.findAll(pageable).toList();
	}

	@Override
	public Stock getStock(int id) {		
		return stockRepository.findById(id)
				              .orElseThrow(() -> new EntityNotFoundException("Not found in DB Exception"));
	}

	@Override
	public Stock createStock(Stock stock) {
		stock.setLastUpdate(new Date());
		return stockRepository.save(stock);
	}

	@Override
	public void deleteStock(int id) {
		stockRepository.deleteById(id);
	}

	@Override
	public Stock updateStock(Stock stock, int id) {
		Stock stockDB = stockRepository.findById(id)
	                                      .orElseThrow(() -> new EntityNotFoundException("Not found in DB Exception"));
		String name = Objects.nonNull(stock.getName()) ? stock.getName() : stockDB.getName();
		Double currentPrice = Objects.nonNull(stock.getCurrentPrice()) ? stock.getCurrentPrice() : stockDB.getCurrentPrice();		
		stockDB = Stock.builder().name(name).currentPrice(currentPrice).lastUpdate(new Date()).build();
		return stockRepository.save(stockDB);
	}
	
}
