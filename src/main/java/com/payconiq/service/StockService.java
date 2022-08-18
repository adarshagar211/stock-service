package com.payconiq.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.payconiq.dto.StockDto;

@Service
public interface StockService {

	public List<StockDto> getAll(int pageNo, int pageSize, String sortBy, String sortDir);

	public StockDto getStock(int id);

	public StockDto createStock(StockDto stock);

	public void deleteStock(int id);

	public StockDto updateStock(StockDto stock, int id);

}
