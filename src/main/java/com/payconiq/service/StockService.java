package com.payconiq.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.payconiq.model.Stock;

@Service
public interface StockService {

	public Page<Stock> getAll(Integer page);

	public Stock getStock(int id);

	public Stock createStock(Stock stock);

	public void deleteStock(int id);

	public Stock updateStock(Stock stock, int id);

}
