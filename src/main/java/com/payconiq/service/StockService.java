package com.payconiq.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.payconiq.model.Stock;

@Service
public interface StockService {

	public List<Stock> getAll(Integer page);

	public Stock getStock(int id);

	public Stock createStock(Stock stock);

	public void deleteStock(int id);

	public Stock updateStock(Stock stock, int id);

}
